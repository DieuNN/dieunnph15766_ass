package com.example.dieunnph15766_ass.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseDB
import com.example.dieunnph15766_ass.database.expense.ExpenseTypeDB
import com.example.dieunnph15766_ass.model.expense.Expense
import com.example.dieunnph15766_ass.model.expense.ExpenseType
import kotlinx.android.synthetic.main.activity_new_expense.*
import java.util.*
import kotlin.collections.ArrayList

class NewExpense : AppCompatActivity() {
    var isEditting1 = false

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = resources.getColor(R.color.dark_green)
        }

        setContentView(R.layout.activity_new_expense)

        setSupportActionBar(toolbar_new_expense)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar_new_expense.setNavigationIcon(R.drawable.outline_close_24)
        title = resources.getString(R.string.add_expense)

        // Set on end icon of text field listener
        setEndIconClickListener()

        // Get data from Income Type
        val database = Database(this)
        var expenseTypeDB = ExpenseTypeDB(database)
        var listExpenseType: ArrayList<ExpenseType> = expenseTypeDB.getAllExpenseType(PreferenceManager.getDefaultSharedPreferences(this).getString("USERNAME", "")!!)

        var listData = ArrayList<String>()

        for (element in listExpenseType) {
            element.expenseTypeName?.let { listData.add(it) }
        }

        if (listData.size == 0) {
            listData.add("")
        }

        val intent = intent
        if (intent != null) {
            isEditting1 = true
            text_input_new_expense_name.setText(intent.getStringExtra("ExpenseName"))
            text_input_new_expense_type.setText(intent.getStringExtra("ExpenseType"))
            text_input_new_expense_date.setText(intent.getStringExtra("ExpenseDate"))
            text_input_new_expense_amount.setText(
                intent.getLongExtra("ExpenseAmount", 0).toString()
            )
            text_input_new_expense_note.setText(intent.getStringExtra("ExpenseNote"))
        }

        // Set data for auto AutoCompleteTextView
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            listData
        )
        text_input_new_expense_type.setAdapter(arrayAdapter)
        button_save_new_expense.setOnClickListener {
            // Validate
            if (validate()) {
                // Call database and insert data

                val expenseDB = ExpenseDB(database)

                if (expenseDB.editExpense(
                        Expense(
                            null,
                            text_input_new_expense_name.text.toString(),
                            text_input_new_expense_date.text.toString(),
                            text_input_new_expense_type.text.toString(),
                            PreferenceManager.getDefaultSharedPreferences(this)
                                .getString("USERNAME", ""),
                            text_input_new_expense_amount.text.toString().toLong(),
                            text_input_new_expense_note.text.toString()
                        ),PreferenceManager.getDefaultSharedPreferences(this)
                            .getString("USERNAME", "")!!,
                    )
                ) {
                    Toast.makeText(
                        this,
                        resources.getString(R.string.successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    val resultIntent = Intent()
                    resultIntent.putExtra("successful", true)
                    setResult(1, resultIntent)
                    onBackPressed()
                } else {
                    if (expenseDB.newExpense(
                            Expense(
                                null,
                                text_input_new_expense_name.text.toString(),
                                text_input_new_expense_date.text.toString(),
                                text_input_new_expense_type.text.toString(),
                                PreferenceManager.getDefaultSharedPreferences(this)
                                    .getString("USERNAME", ""),
                                text_input_new_expense_amount.text.toString().toLong(),
                                text_input_new_expense_note.text.toString()
                            )
                        )
                    ) {
                        Toast.makeText(
                            this,
                            resources.getString(R.string.successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                        val resultIntent = Intent()
                        resultIntent.putExtra("successful", true)
                        setResult(0, resultIntent)
                        onBackPressed()
                    }
                }
            }
        }


// If user did not choose date yet, it becomes error, when view lost focus, remove error message
        text_input_new_expense_date.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                text_input_layout_new_expense_date.error = null
            }
        }


    }

    private fun validate(): Boolean {
        if (text_input_new_expense_name.text.toString().isEmpty()) {
            text_input_layout_new_expense_name.error = resources.getString(R.string.cannot_be_empty)
            return false
        } else {
            text_input_layout_new_expense_name.error = null
        }
        if (text_input_new_expense_note.text.toString().isEmpty()) {
            text_input_layout_new_expense_note.error = resources.getString(R.string.cannot_be_empty)
            return false
        } else {
            text_input_layout_new_expense_note.error = null
        }
        if (text_input_new_expense_type.text.toString().isEmpty()) {
            text_input_layout_new_expense_type.error = resources.getString(R.string.didnot_choose)
            return false
        } else {
            text_input_layout_new_expense_type.error = null
        }
        if (text_input_new_expense_date.text.toString().isEmpty()) {
            text_input_layout_new_expense_date.error =
                resources.getString(R.string.didnot_choose)
            return false
        } else {
            text_input_layout_new_expense_date.error = null
        }
        if (text_input_new_expense_amount.text.toString().isEmpty()) {
            text_input_layout_new_expense_amount.error =
                resources.getString(R.string.cannot_be_empty)
        } else {
            text_input_layout_new_expense_amount.error = null
        }
        return true
    }

    private fun setEndIconClickListener() {
        text_input_layout_new_expense_date.setEndIconOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            // New date picker dialog
            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                let {
                    text_input_new_expense_date.setText("$dayOfMonth/${month + 1}/$year")
                }
            }, year, month, day)
            datePickerDialog.show()
        }
    }
}