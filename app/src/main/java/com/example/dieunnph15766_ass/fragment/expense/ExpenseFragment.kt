package com.example.dieunnph15766_ass.fragment.expense

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.activity.NewExpense
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpenseFragment : Fragment() {

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

        fab.setOnClickListener {
            startActivity(Intent(requireContext(), NewExpense::class.java))
        }
    }
}