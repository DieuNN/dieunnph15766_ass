package com.example.dieunnph15766_ass.fragment.income

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.adapter.IncomeTypeArrayAdapter
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.income.IncomeTypeDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.dieunnph15766_ass.dialog.CustomDialogNewIncomeType
import com.example.dieunnph15766_ass.model.income.IncomeType

class IncomeTypeFragment : Fragment(), CustomDialogNewIncomeType.OnInputSelected {
    lateinit var adapter: ArrayAdapter<IncomeType>
    lateinit var database:Database
    lateinit var incomeTypeList:ArrayList<IncomeType>

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

        val listView = view.findViewById<ListView>(R.id.listview_income_type)
        database = Database(parentFragment?.activity)
        val incomeTypeDB = IncomeTypeDB(database)
        incomeTypeList = incomeTypeDB.getAllIncomeType(PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString("USERNAME", "")!!)


        adapter = IncomeTypeArrayAdapter(requireContext(), R.layout.income_type_row, incomeTypeList)
        listView.adapter = adapter

        val fab =
            view.findViewById<View>(R.id.floating_action_button_add_new_income_type) as FloatingActionButton
        fab.setOnClickListener {
            var dialog = CustomDialogNewIncomeType()
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager, "Tag")
        }
    }

    override fun setText(text: String) {
        // Insert into database
        database = Database(parentFragment?.activity)
        val incomeTypeDB = IncomeTypeDB(database)
        val username = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "")
        if(incomeTypeDB.newIncomeType(IncomeType(null, text, username))) {
            Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show()
            adapter.apply {
                clear()
                addAll(incomeTypeDB.getAllIncomeType(PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .getString("USERNAME", "")!!))
                this.notifyDataSetChanged()
            }
        } else  {
            Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show()
        }
    }


}