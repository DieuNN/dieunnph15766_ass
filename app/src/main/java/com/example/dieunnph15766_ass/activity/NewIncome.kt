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
import com.example.dieunnph15766_ass.database.income.IncomeDB
import com.example.dieunnph15766_ass.database.income.IncomeTypeDB
import com.example.dieunnph15766_ass.model.income.Income
import com.example.dieunnph15766_ass.model.income.IncomeType
import kotlinx.android.synthetic.main.activity_new_income.*
import java.util.*
import kotlin.collections.ArrayList

class NewIncome : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = resources.getColor(R.color.dark_green)
        }

        setContentView(R.layout.activity_new_income)

        setSupportActionBar(toolbar_new_income)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar_new_income.setNavigationIcon(R.drawable.outline_close_24)
        title = resources.getString(R.string.add_income)

        // Set on end icon of text field listener
        setEndIconClickListener()

        // Get data from Income Type
        val database = Database(this)
        var incomeTypeDB = IncomeTypeDB(database)
        var listIncomeType = ArrayList<IncomeType>()
        listIncomeType = incomeTypeDB.getAllIncomeType()

        var listData = ArrayList<String>()

        for (element in listIncomeType) {
            element.incomeTypeName?.let { listData.add(it) }
        }

        if(listData.size == 0) {
            listData.add("")
        }

        // Set data for auto AutoCompleteTextView
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            listData
        )
        text_input_new_income_type.setAdapter(arrayAdapter)
        button_save_new_income.setOnClickListener {
            // Validate
            if (validate()) {
                // Call database and insert data

                val incomeDB = IncomeDB(database)

                if (incomeDB.newIncome(
                        Income(
                             incomeID = null,
                            text_input_new_income_name.text.toString(),
                            text_input_new_income_date.text.toString(),
                            text_input_new_income_type.text.toString(),
                            PreferenceManager.getDefaultSharedPreferences(this)
                                .getString("USERNAME", ""),
                            text_input_new_income_amount.text.toString().toLong()
                        )
                    )
                ) {
                    Toast.makeText(this, resources.getString(R.string.successfully), Toast.LENGTH_SHORT).show()
                    val resultIntent = Intent()
                    resultIntent.putExtra("successful", true)
                    setResult(0, resultIntent)
                    onBackPressed()
                } else {
                    Toast.makeText(this, resources.getString(R.string.failed), Toast.LENGTH_SHORT).show()
                }

            }
        }

        // If user did not choose date yet, it becomes error, when view lost focus, remove error message
        text_input_new_income_date.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                text_input_layout_new_income_date.error = null
            }
        }


    }

    private fun validate(): Boolean {
        if (text_input_new_income_name.text.toString().isEmpty()) {
            text_input_layout_new_income_name.error = resources.getString(R.string.cannot_be_empty)
            return false
        } else {
            text_input_layout_new_income_name.error = null
        }
        if (text_input_new_income_note.text.toString().isEmpty()) {
            text_input_layout_new_income_note.error = resources.getString(R.string.cannot_be_empty)
            return false
        } else {
            text_input_layout_new_income_note.error = null
        }
        if (text_input_new_income_type.text.toString().isEmpty()) {
            text_input_layout_new_income_type.error = resources.getString(R.string.didnot_choose)
            return false
        } else {
            text_input_layout_new_income_type.error = null
        }
        if (text_input_new_income_date.text.toString().isEmpty()) {
            text_input_layout_new_income_date.error =
                resources.getString(R.string.didnot_choose)
            return false
        } else {
            text_input_layout_new_income_date.error = null
        }
        if (text_input_new_income_amount.text.toString().isEmpty()) {
            text_input_layout_new_income_amount.error =
                resources.getString(R.string.cannot_be_empty)
        } else {
            text_input_layout_new_income_amount.error = null
        }
        return true
    }

    private fun setEndIconClickListener() {
        text_input_layout_new_income_date.setEndIconOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            // New date picker dialog
            val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                let {
                    text_input_new_income_date.setText("$dayOfMonth/${month + 1}/$year")
                }
            }, year, month, day)
            datePickerDialog.show()
        }
    }


}