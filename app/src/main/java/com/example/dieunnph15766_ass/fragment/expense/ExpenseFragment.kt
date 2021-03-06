package com.example.dieunnph15766_ass.fragment.expense

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewExpense
import com.example.dieunnph15766_ass.adapter.RecyclerViewExpenseAdapter
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseDB
import com.example.dieunnph15766_ass.model.expense.Expense
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpenseFragment : Fragment() {
    lateinit var adapter: RecyclerViewExpenseAdapter
    lateinit var database: Database
    lateinit var expenseDB: ExpenseDB
    lateinit var expenseList: ArrayList<Expense>
    lateinit var recyclerview: RecyclerView
    var isInsertSuccess: Boolean? = false
    lateinit var userName:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_new_expense)
        recyclerview = view.findViewById(R.id.recyclerview_expense)
        database = Database(requireContext())
        expenseDB = ExpenseDB(database)
        userName = PreferenceManager.getDefaultSharedPreferences(context).getString("USERNAME", "")!!

        expenseList = expenseDB.getAllExpense(userName!!)

        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)

        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerview.adapter = adapter


        fab.setOnClickListener {
            startActivityForResult(Intent(requireContext(), NewExpense::class.java), 101)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        isInsertSuccess = data?.getBooleanExtra("successful", false)
        expenseList.clear()
        expenseList = expenseDB.getAllExpense(userName)
        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)
        recyclerview.adapter = adapter

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            RecyclerViewExpenseAdapter.DELETE -> apply {
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("X??c nh???n xo??")
                    setMessage("B???n c?? ch???c mu???n xo?? kh??ng?")
                    setNegativeButton("Hu???") { dialog, _ ->
                        dialog.dismiss()
                    }
                    setPositiveButton("Xo??") { _, _ ->
                        adapter.removeExpense(item.groupId)
                        Toast.makeText(requireContext(), "Xo?? th??nh c??ng!", Toast.LENGTH_SHORT)
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
        expenseList = expenseDB.getAllExpense(userName)
        adapter = RecyclerViewExpenseAdapter(requireContext(), expenseList)
        recyclerview.adapter = adapter
    }
}