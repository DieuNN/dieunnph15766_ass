package com.example.dieunnph15766_ass.fragment.income

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewIncome
import com.example.dieunnph15766_ass.adapter.RecyclerViewIncomeAdapter
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.income.IncomeDB
import com.example.dieunnph15766_ass.model.income.Income
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncomeFragment
    : Fragment() {
    lateinit var adapter:RecyclerViewIncomeAdapter
    lateinit var database:Database
    lateinit var incomeDB:IncomeDB
    lateinit var incomeList:ArrayList<Income>
    lateinit var recyclerview:RecyclerView
    var isInsertSuccess:Boolean? = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerview = view.findViewById<RecyclerView>(R.id.recycleview_income)
        database = Database(requireContext())
        incomeDB = IncomeDB(database)

        incomeList = incomeDB.getAllIncomes(
            PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString("USERNAME", "")!!)

        adapter = RecyclerViewIncomeAdapter(requireContext(), incomeList)

        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.fab_new_income)

        floatingActionButton.setOnClickListener {
            startActivityForResult(Intent(requireContext(), NewIncome::class.java), 0)
        }

        recyclerview.addOnScrollListener( object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1)) {
                    floatingActionButton.apply {
                        visibility = View.INVISIBLE
                        isEnabled = false
                    }
                } else {
                    floatingActionButton.apply {
                        visibility = View.VISIBLE
                        isEnabled = true
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//                isInsertSuccess = data?.getBooleanExtra("successful", false)
                incomeList.clear()
                incomeList = incomeDB.getAllIncomes(PreferenceManager.getDefaultSharedPreferences(requireContext())
                    .getString("USERNAME", "")!!)
                adapter = RecyclerViewIncomeAdapter(requireContext(), incomeList)
                recyclerview.adapter = adapter
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            RecyclerViewIncomeAdapter.DELETE -> apply {
                AlertDialog.Builder(requireContext())
                    .apply {
                        setTitle("Xác nhận xoá")
                        setMessage("Bạn có chắc muốn xoá không?")
                        setNegativeButton("Huỷ") {dialog, _ ->
                            dialog.dismiss()
                        }
                        setPositiveButton("Xoá") {_, _ ->
                            adapter.removeIncome(item.groupId)
                            Toast.makeText(requireContext(), "Xoá thành công!", Toast.LENGTH_SHORT).show()
                        }
                        create()
                        show()
                    }

            }
            RecyclerViewIncomeAdapter.EDIT -> apply {
                adapter.editIncome(item.groupId)
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        incomeList.clear()
        incomeList = incomeDB.getAllIncomes(PreferenceManager.getDefaultSharedPreferences(requireContext())
            .getString("USERNAME", "")!!)
        adapter = RecyclerViewIncomeAdapter(requireContext(), incomeList)
        recyclerview.adapter = adapter
    }
}