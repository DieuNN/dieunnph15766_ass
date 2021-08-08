package com.example.dieunnph15766_ass.adapter

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.model.income.Income
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewIncomeAdapter(private val mList: ArrayList<Income>) :
    RecyclerView.Adapter<RecyclerViewIncomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_income, null)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)     {
        holder.name.text = mList[position].incomeName
        holder.amount.text = mList[position].incomeAmount.toString()
        holder.date.text = "Ngày ${mList[position].incomeDate}"
        holder.type.text = "Loại: ${mList[position].incomeTypeName} "

        // Month not implement yet.


//        val date: Date = java.text.SimpleDateFormat("dd/MM/yyyy").parse(holder.date.text.toString())
//        val monthParsed = date.month
//
//        holder.month.text = "Tháng ${monthParsed + 1}"
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnCreateContextMenuListener {
        val month: TextView = itemView.findViewById(R.id.textview_cardview_income_month)
        val amount: TextView = itemView.findViewById(R.id.textview_cardview_income_amount)
        val date: TextView = itemView.findViewById(R.id.textview_cardview_income_date)
        val name: TextView = itemView.findViewById(R.id.textview_cardview_income_name)
        val type:TextView = itemView.findViewById(R.id.textview_cardview_income_type_name)
        var cardview:CardView = itemView.findViewById(R.id.cardview_income)

        init {
            cardview.setOnCreateContextMenuListener(this)
        }
        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(adapterPosition, 121, 0, "Delete")
            menu?.add(adapterPosition, 122, 1, "Edit")
        }
    }
}