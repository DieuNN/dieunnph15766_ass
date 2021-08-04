package com.example.dieunnph15766_ass.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dieunnph15766_ass.R
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.example.dieunnph15766_ass.adapter.ContainerFragmentAdapter

class IncomeFragment  //TODO: Any time you called parent fragment, it'll be called till out of memory, so you should have container fragments
    : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        return view
    }


}