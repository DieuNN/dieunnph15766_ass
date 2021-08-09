package com.example.dieunnph15766_ass.fragment.expense

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewExpense
import com.example.dieunnph15766_ass.adapter.RecyclerViewExpenseAdapter
import com.example.dieunnph15766_ass.adapter.RecyclerViewIncomeAdapter
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseDB
import com.example.dieunnph15766_ass.database.income.IncomeDB
import com.example.dieunnph15766_ass.model.expense.Expense
import com.example.dieunnph15766_ass.model.income.Income
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpenseFragment : Fragment() {
    lateinit var adapter: RecyclerViewExpenseAdapter
    lateinit var database: Database
    lateinit var expenseDB: ExpenseDB
    lateinit var expenseList: ArrayList<Expense>
    lateinit var recyclerview: RecyclerView
    var isInsertSuccess: Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_new_expense)
        recyclerview = view.findViewById(R.id.recyclerview_expense)
        database = Database(requireContext())
        expenseDB = ExpenseDB(database)

        expenseList = expenseDB.getAllExpense()

        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = adapter


        fab.setOnClickListener {
            startActivityForResult(Intent(requireContext(), NewExpense::class.java), 101)
        }

        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerview.canScrollVertically(1)) {
                    fab.apply {
                        visibility = View.INVISIBLE
                        isEnabled = false
                    }
                } else {
                    fab.apply {
                        visibility = View.VISIBLE
                        isEnabled = true
                    }
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        isInsertSuccess = data?.getBooleanExtra("successful", false)
        expenseList.clear()
        expenseList = expenseDB.getAllExpense()
        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)
        recyclerview.adapter = adapter

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            RecyclerViewExpenseAdapter.DELETE -> apply {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Xác nhận xoá")
                    setMessage("Bạn có chắc muốn xoá không?")
                    setNegativeButton("Huỷ") { dialog, _ ->
                        dialog.dismiss()
                    }
                    setPositiveButton("Xoá") { _, _ ->
                        adapter.removeExpense(item.groupId)
                        Toast.makeText(requireContext(), "Xoá thành công!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    create()
                    show()
                }
            }
            RecyclerViewExpenseAdapter.EDIT -> {
                adapter.editExpense(item.groupId)
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        expenseList.clear()
        expenseList = expenseDB.getAllExpense()
        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)
        recyclerview.adapter = adapter
    }
}