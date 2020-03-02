package com.tafi.footballspin.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var mPagerAdapter: MainPagerAdapter

    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)

    }

    override fun initView() {

        view_pager.adapter = mPagerAdapter
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null)
                    prevMenuItem!!.isChecked = false
                else
                    bottom_navigation.menu.getItem(0).isChecked = false

                bottom_navigation.menu.getItem(position).isChecked = true
                prevMenuItem = bottom_navigation.menu.getItem(position)
            }
        })

        bottom_navigation.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bottom_navigation_item_home -> {
                view_pager.currentItem = 0
                true
            }
            R.id.bottom_navigation_item_result -> {
                view_pager.currentItem = 1
                true
            }
            R.id.bottom_navigation_item_settings -> {
                view_pager.currentItem = 2
                true
            }
            else -> false
        }
    }

}
