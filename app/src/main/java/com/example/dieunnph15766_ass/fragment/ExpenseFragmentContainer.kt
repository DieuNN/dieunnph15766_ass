package com.example.dieunnph15766_ass.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.adapter.ContainerFragmentAdapter
import com.google.android.material.tabs.TabLayout

class ExpenseFragmentContainer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_outcome_container, container, false)
        val viewPager = view.findViewById<ViewPager>(R.id.viewpager_outcome_container)
        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout_outcome_container)
        val adapter = ContainerFragmentAdapter(childFragmentManager)

        tabLayout.setupWithViewPager(viewPager)
        adapter.addFragment(OutcomeFragment(), resources.getString(R.string.outcome))
        adapter.addFragment(OutcomeTypeFragment(), resources.getString(R.string.outcome_type))

        viewPager.adapter = adapter



        return view

    }
}