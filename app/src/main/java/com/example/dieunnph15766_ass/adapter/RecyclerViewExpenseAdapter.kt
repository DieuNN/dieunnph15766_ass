package com.example.dieunnph15766_ass.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.model.expense.Expense

class RecyclerViewExpenseAdapter(private val mList: ArrayList<Expense>) :
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var month: TextView = itemView.findViewById(R.id.textview_cardview_expense_month)
        var amount: TextView = itemView.findViewById(R.id.textview_cardview_expense_amount)
        var date: TextView = itemView.findViewById(R.id.textview_cardview_expense_date)
        var name: TextView = itemView.findViewById(R.id.textview_cardview_expense_name)
        val type: TextView = itemView.findViewById(R.id.textview_cardview_expense_type_name)
    }
}