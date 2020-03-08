package com.tafi.footballspin.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tafi.footballspin.ui.main.home.HomeFragment
import com.tafi.footballspin.ui.main.result.ResultFragment
import com.tafi.footballspin.ui.main.settings.SettingsFragment

/**
 * Created by taind-201 on 3/1/2020.
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listFragment = mutableListOf<Fragment>()

    init {
        listFragment.add(HomeFragment())
        listFragment.add(ResultFragment())
        listFragment.add(SettingsFragment())
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }
}
