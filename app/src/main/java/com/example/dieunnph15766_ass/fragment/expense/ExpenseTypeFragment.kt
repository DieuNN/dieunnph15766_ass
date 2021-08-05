package com.example.dieunnph15766_ass.fragment.expense

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseTypeDB
import com.example.dieunnph15766_ass.dialog.CustomDialogNewExpenseType
import com.example.dieunnph15766_ass.dialog.CustomDialogNewIncomeType
import com.example.dieunnph15766_ass.model.expense.ExpenseType
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpenseTypeFragment : Fragment(), CustomDialogNewExpenseType.OnInputSelected {
    lateinit var textResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container!!.context)
            .inflate(R.layout.fragment_expense_type, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textResult = view.findViewById<TextView>(R.id.recieved_from_custom_dialog_fragment_new_expense_type)
        textResult.text = "Text example"
        val fab =
            view.findViewById<View>(R.id.fab_new_income_type) as FloatingActionButton
        fab.setOnClickListener {
            var dialog = CustomDialogNewExpenseType()
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager, "Tag")
        }

    }

    override fun setText(text: String) {
        // Insert into database
        val database = Database(parentFragment?.activity)
        val expenseTypeDB = ExpenseTypeDB(database)
        textResult.text = text
        val username = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "")
        if(expenseTypeDB.newExpenseType(ExpenseType(null, text, username))) {
            Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show()
        } else  {
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show()
        }
    }
}