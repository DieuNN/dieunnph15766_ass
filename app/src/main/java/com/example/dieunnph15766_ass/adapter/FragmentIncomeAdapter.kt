package com.example.dieunnph15766_ass.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycling
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dieunnph15766_ass.fragment.IncomeFragment
import com.example.dieunnph15766_ass.fragment.IncomeTypeFragment

class FragmentIncomeAdapter(private val fragmentManager: FragmentManager, private val lifeCycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return if(position == 0) IncomeFragment() else IncomeTypeFragment()
    }
}