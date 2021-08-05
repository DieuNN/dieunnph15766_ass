package com.example.dieunnph15766_ass.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.MainActivity
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.IncomeTypeDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.dieunnph15766_ass.dialog.CustomDialog
import com.example.dieunnph15766_ass.model.IncomeType

class IncomeTypeFragment : Fragment(), CustomDialog.OnInputSelected {
    lateinit var textResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container!!.context)
            .inflate(R.layout.fragment_income_type, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textResult = view.findViewById<TextView>(R.id.textview_recieve_from_custom_dialog)
        textResult.text = "Text example"
        val fab =
            view.findViewById<View>(R.id.floating_action_button_add_new_income_type) as FloatingActionButton
        fab.setOnClickListener {
            var dialog = CustomDialog()
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager, "Tag")
        }

    }

    override fun setText(text: String) {
        // Insert into database
        val database = Database(parentFragment?.activity)
        val incomeTypeDB = IncomeTypeDB(database)
        textResult.text = text
        val username = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "")
        if(incomeTypeDB.newIncomeType(IncomeType(null, text, username))) {
            Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show()
        } else  {
            Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show()
        }
    }
}