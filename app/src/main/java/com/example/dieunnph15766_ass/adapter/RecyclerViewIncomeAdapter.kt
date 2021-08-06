package com.example.dieunnph15766_ass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.model.income.Income

class RecyclerViewIncomeAdapter() : RecyclerView.Adapter<RecyclerViewIncomeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textview:TextView = itemView.findViewById(R.id.textview_expense_type_name_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_view_income, null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}