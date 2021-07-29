package com.example.dieunnph15766_ass.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.adapter.FragmentIncomeAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_income_.*
import kotlinx.android.synthetic.main.fragment_income_.view.*


class IncomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_income_, container, false)
        val fragmentManager:FragmentManager = parentFragmentManager
        view.view_pager2_income.adapter = FragmentIncomeAdapter(fragmentManager, lifecycle)
        view.tab_layout_income_controller.addTab(view.tab_layout_income_controller.newTab().setText("tab1"))
        view.tab_layout_income_controller.addTab(view.tab_layout_income_controller.newTab().setText("tab2"))



        return view
    }


}


