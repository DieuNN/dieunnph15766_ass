package com.example.dieunnph15766_ass.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewIncome
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IncomeFragment
    : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.fab_new_income)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(context, NewIncome::class.java))
        }
    }
}