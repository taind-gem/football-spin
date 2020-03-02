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

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ResultFragment()
            else -> SettingsFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
