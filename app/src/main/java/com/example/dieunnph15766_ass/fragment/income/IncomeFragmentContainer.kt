package com.example.dieunnph15766_ass.fragment.income

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.adapter.ContainerFragmentAdapter
import com.google.android.material.tabs.TabLayout

class IncomeFragmentContainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_income_container, container, false)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager_income_container)
        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout_income_container)
        val adapter = ContainerFragmentAdapter(childFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
        adapter.addFragment(IncomeFragment(), resources.getString(R.string.income))
        adapter.addFragment(IncomeTypeFragment(), resources.getString(R.string.income_type))

        viewPager.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val database = Database(activity)
//        val incomeTypeDB = IncomeTypeDB(database)
//
//        incomeTypeDB.newIncomeType(IncomeType(null, "alo", "dieu"))

    }
}