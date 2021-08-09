package com.example.dieunnph15766_ass.fragment

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseDB
import com.example.dieunnph15766_ass.database.expense.ExpenseTypeDB
import com.example.dieunnph15766_ass.database.income.IncomeDB
import com.example.dieunnph15766_ass.database.income.IncomeTypeDB
import com.example.dieunnph15766_ass.model.expense.Expense
import com.example.dieunnph15766_ass.model.expense.ExpenseType
import com.example.dieunnph15766_ass.model.income.Income
import com.example.dieunnph15766_ass.model.income.IncomeType
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_new_income.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ChartFragment : Fragment() {
    lateinit var incomeChart: PieChart
    lateinit var expenseChart: PieChart
    lateinit var database: Database
    lateinit var incomeDB: IncomeDB
    lateinit var expenseDB: ExpenseDB
    lateinit var incomeTypeDB: IncomeTypeDB
    lateinit var expenseTypeDB: ExpenseTypeDB
    lateinit var total: TextView
    lateinit var incomeTotal: TextView
    lateinit var expenseTotal: TextView
    lateinit var userName:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var startDayLayout = view.findViewById<TextInputLayout>(R.id.edit_layout_start_day)
        var endDayLayout = view.findViewById<TextInputLayout>(R.id.edit_layout_end_day)
        var startDay = view.findViewById<TextInputEditText>(R.id.edittext_start_day)
        var endDay = view.findViewById<TextInputEditText>(R.id.edittext_end_day)
        var dayError = view.findViewById<TextView>(R.id.textview_day_input_error)
        var buttonCalculate = view.findViewById<Button>(R.id.button_calculate)
        incomeTotal = view.findViewById<TextView>(R.id.txt_total_income)
        expenseTotal = view.findViewById<TextView>(R.id.txt_total_expense)
        total = view.findViewById<TextView>(R.id.txt_total)
        incomeChart = view.findViewById(R.id.chart_income)
        expenseChart = view.findViewById(R.id.chart_expense)
        database = Database(requireContext())
        incomeDB = IncomeDB(database)
        expenseDB = ExpenseDB(database)
        incomeTypeDB = IncomeTypeDB(database)
        expenseTypeDB = ExpenseTypeDB(database)
        userName = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "")!!
        startDayLayout.setEndIconOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            // New date picker dialog
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    let {
                        startDay.setText("$dayOfMonth/${month + 1}/$year")
                    }
                }, year, month, day)
            datePickerDialog.show()
        }

        endDayLayout.setEndIconOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            // New date picker dialog
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    let {
                        endDay.setText("$dayOfMonth/${month + 1}/$year")
                    }
                }, year, month, day)
            datePickerDialog.show()
        }
        buttonCalculate.setOnClickListener {
            dayError.visibility = View.INVISIBLE
            if (startDay.text!!.isEmpty() || endDay.text!!.isEmpty()) {
                dayError.text = "Không thể để trống!"
                dayError.visibility = View.VISIBLE
                return@setOnClickListener
            }
            when (parseStringToDate(startDay.text.toString()).compareTo(parseStringToDate(endDay.text.toString()))) {
                -1 -> apply {
                    dayError.visibility = View.INVISIBLE
                    incomeTotal.text = getTotalIncome(
                        incomeDB.getAllIncomes(PreferenceManager.getDefaultSharedPreferences(requireContext())
                            .getString("USERNAME", "")!!),
                        startDay.text.toString(),
                        endDay.text.toString()
                    ).toString()
                    expenseTotal.text = getTotalExpense(
                        expenseDB.getAllExpense(userName),
                        startDay.text.toString(),
                        endDay.text.toString()
                    ).toString()
                    total.text = (incomeTotal.text.toString().toInt() - expenseTotal.text.toString()
                        .toInt()).toString()

                    if(incomeDB.getAllIncomes(PreferenceManager.getDefaultSharedPreferences(requireContext())
                            .getString("USERNAME", "")!!).size == 0 || incomeTypeDB.getAllIncomeType(PreferenceManager.getDefaultSharedPreferences(requireContext())
                            .getString("USERNAME", "")!!).size == 0 || expenseDB.getAllExpense(userName).size == 0 || expenseTypeDB.getAllExpenseType(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("USERNAME", "")!!).size == 0) {
                        dayError.text = "Chưa đủ dữ liệu để thống kê!"
                        dayError.visibility = View.VISIBLE
                        return@setOnClickListener
                    }

                    try {
                        setupIncomePieChart()
                        incomePieChartData()
                        setupExpensePieChart()
                        expensePieChartData()
                    } catch (e:Exception) {
                        dayError.text = "Có lỗi với dữ liệu nhập vào, kiểm tra lại!"
                    }

                }
                1 -> apply {
                    dayError.visibility = View.VISIBLE
                    dayError.text = "Ngày bắt đầu lớn hơn ngày kết thúc, kiểm tra lại"
                }
                0 -> apply {
                    dayError.visibility = View.VISIBLE
                    dayError.text = "Ngày bắt đầu bằng ngày kết thúc, kiểm tra lại"
                }
            }
        }
    }

    fun parseStringToDate(inputDay: String): Date {
        return SimpleDateFormat("dd/MM/yyyy").parse(inputDay)
    }

    fun parseDateToString(inputDay: Date): String {
        return SimpleDateFormat("dd/MM/yyyy").format(inputDay)
    }

    fun getTotalIncome(incomeList: ArrayList<Income>, startDay: String, endDay: String): Long {
        var startDayParsed = parseStringToDate(startDay)
        var endDayParsed = parseStringToDate(endDay)
        var result: Long = 0

        for (element in incomeList) {
            if (parseStringToDate(element.incomeDate!!).after(startDayParsed) && parseStringToDate(
                    element.incomeDate!!
                ).before(endDayParsed)
            ) {
                result += element.incomeAmount!!
            }
        }
        return result
    }

    fun getTotalExpense(expenseList: ArrayList<Expense>, startDay: String, endDay: String): Long {
        var startDayParsed = parseStringToDate(startDay)
        var endDayParsed = parseStringToDate(endDay)
        var result: Long = 0

        for (element in expenseList) {
            if (parseStringToDate(element.expenseDate!!).after(startDayParsed) && parseStringToDate(
                    element.expenseDate!!
                ).before(endDayParsed)
            ) {
                result += element.expenseAmount!!
            }
        }
        return result
    }

    fun getTotalIncomeForEachIncomeType(
        incomeList: ArrayList<Income>,
        incomeTypeList: ArrayList<IncomeType>
    ): ArrayList<Long> {
        var result = ArrayList<Long>()
        var total: Long = 0
        for (position in 0 until incomeTypeList.size) {
            for (element in incomeList) {
                if (incomeTypeList[position].incomeTypeName!! == element.incomeTypeName) {
                    total += element.incomeAmount!!
                }
            }
            result.add(total)
            total = 0
        }
        return result
    }

    fun getTotalExpenseForEachIncomeType(
        expenseList: ArrayList<Expense>,
        expenseTypeList: ArrayList<ExpenseType>
    ): ArrayList<Long> {
        var result = ArrayList<Long>()
        var total: Long = 0

        for (position in 0 until expenseTypeList.size) {
            for (element in expenseList) {
                if (expenseTypeList[position].expenseTypeName == element.expenseTypeName) {
                    total += element.expenseAmount!!
                }
            }
            result.add(total)
            total = 0
        }

        return result
    }


    fun incomePieChartData() {
        val entries = ArrayList<PieEntry>()
        var color = ArrayList<Int>()
        var totalIncomeForEachType = getTotalIncomeForEachIncomeType(
            incomeDB.getAllIncomes(PreferenceManager.getDefaultSharedPreferences(requireContext())
                .getString("USERNAME", "")!!),
            incomeTypeDB.getAllIncomeType(PreferenceManager.getDefaultSharedPreferences(requireContext())
                .getString("USERNAME", "")!!)
        )
        val allIncomeType = incomeTypeDB.getAllIncomeType(PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString("USERNAME", "")!!)
        val totalIncome = incomeTotal.text.toString().toLong()

        for (i in 0 until allIncomeType.size) {
            entries.add(
                PieEntry(
                    (totalIncomeForEachType[i].toFloat() / totalIncome.toFloat()),
                    allIncomeType[i].incomeTypeName
                )
            )
        }


        for (element in ColorTemplate.MATERIAL_COLORS) {
            color.add(element)
        }

        for (element in ColorTemplate.VORDIPLOM_COLORS) {
            color.add(element)
        }

        var dataSet = PieDataSet(entries, "Loại thu")
        dataSet.colors = color

        val data = PieData(dataSet)
        data.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(incomeChart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }
        incomeChart.data = data
        incomeChart.invalidate()
        incomeChart.animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInQuad)
    }

    fun setupIncomePieChart() {
        incomeChart.isDrawHoleEnabled = true
        incomeChart.apply {
            setUsePercentValues(true)
            setEntryLabelTextSize(12f)
            setEntryLabelColor(Color.BLACK)
            centerText = "Thu"
            setCenterTextSize(24f)
            description.isEnabled = true

            val legend = legend

            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.setDrawInside(false)
            legend.isEnabled = true
        }


    }

    fun expensePieChartData() {
        val entries = ArrayList<PieEntry>()
        var color = ArrayList<Int>()
        var totalExpenseForEachType = getTotalExpenseForEachIncomeType(
            expenseDB.getAllExpense(userName),
            expenseTypeDB.getAllExpenseType(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("USERNAME", "")!!)
        )
        val totalExpense = expenseTotal.text.toString().toLong()
        val allExpenseType = expenseTypeDB.getAllExpenseType(PreferenceManager.getDefaultSharedPreferences(requireContext()).getString("USERNAME", "")!!)

        for (i in 0 until allExpenseType.size) {
            entries.add(
                PieEntry(
                    (totalExpenseForEachType[i].toFloat() / totalExpense.toFloat()),
                    allExpenseType[i].expenseTypeName
                )
            )
        }



        for (element in ColorTemplate.MATERIAL_COLORS) {
            color.add(element)
        }

        for (element in ColorTemplate.VORDIPLOM_COLORS) {
            color.add(element)
        }

        var dataSet = PieDataSet(entries, "Loại chi")
        dataSet.colors = color

        val data = PieData(dataSet)
        data.apply {
            setDrawValues(true)
            setValueFormatter(PercentFormatter(expenseChart))
            setValueTextSize(12f)
            setValueTextColor(Color.BLACK)
        }
        expenseChart.data = data
        expenseChart.invalidate()
        expenseChart.animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInQuad)
    }


    fun setupExpensePieChart() {
        expenseChart.isDrawHoleEnabled = true
        expenseChart.apply {
            setUsePercentValues(true)
            setEntryLabelTextSize(12f)
            setEntryLabelColor(Color.BLACK)
            centerText = "Chi"
            setCenterTextSize(24f)
            description.isEnabled = true

            val legend = legend

            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.setDrawInside(false)
            legend.isEnabled = true
        }
    }
}