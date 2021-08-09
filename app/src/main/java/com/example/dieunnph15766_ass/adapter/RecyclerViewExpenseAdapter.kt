package com.example.dieunnph15766_ass.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewExpense
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseDB
import com.example.dieunnph15766_ass.model.expense.Expense

class RecyclerViewExpenseAdapter(private val mContext:Context ,private var mList: ArrayList<Expense>) :
    RecyclerView.Adapter<RecyclerViewExpenseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewExpenseAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_expense, null)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewExpenseAdapter.ViewHolder, position: Int) {
        holder.amount.text = mList[position].expenseAmount.toString()
        holder.date.text = "Ngày ${mList[position].expenseDate}"

        // Month not implement yet.
        holder.type.text = "Loại: ${mList[position].expenseTypeName}"
        holder.name.text = mList[position].expenseName
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener {
        var month: TextView = itemView.findViewById(R.id.textview_cardview_expense_month)
        var amount: TextView = itemView.findViewById(R.id.textview_cardview_expense_amount)
        var date: TextView = itemView.findViewById(R.id.textview_cardview_expense_date)
        var name: TextView = itemView.findViewById(R.id.textview_cardview_expense_name)
        var type: TextView = itemView.findViewById(R.id.textview_cardview_expense_type_name)
        var cardView = itemView.findViewById<CardView>(R.id.cardview_expense)

        init {
            cardView.setOnCreateContextMenuListener(this)

        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(adapterPosition, DELETE, 0, "Delete")
            menu?.add(adapterPosition, EDIT, 1, "Edit")
        }
    }

    companion object {
        const val DELETE = 131
        const val EDIT = 132
    }

    fun removeExpense(position: Int) {
        val database = Database(mContext)
        val expenseDB = ExpenseDB(database)

        expenseDB.removeExpense(mList[position])
        mList.clear()
        mList = expenseDB.getAllExpense()
        this.notifyDataSetChanged()
    }

    fun editExpense(position: Int) {
        val intent = Intent(mContext, NewExpense::class.java)
        intent.putExtra("ExpenseName", mList[position].expenseName)
        intent.putExtra("ExpenseType", mList[position].expenseTypeName)
        intent.putExtra("ExpenseDate", mList[position].expenseDate)
        intent.putExtra("ExpenseAmount", mList[position].expenseAmount)

        (mContext as Activity).startActivityForResult(intent, 101)

        val database = Database(mContext)
        val expenseDB = ExpenseDB(database)

        expenseDB.editExpense(mList[position])
    }


}