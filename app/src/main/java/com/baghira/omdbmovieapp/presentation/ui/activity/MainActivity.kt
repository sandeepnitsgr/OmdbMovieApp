package com.baghira.omdbmovieapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.databinding.ActivityMainBinding
import com.baghira.omdbmovieapp.utils.NetworkUtils
import com.baghira.omdbmovieapp.utils.Utils
import com.baghira.omdbmovieapp.presentation.ui.adapter.ViewStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initNetworkStateListener()
        initViewPagerContent()
    }

    private fun initNetworkStateListener() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(this, { isConnected ->
            if (!isConnected) {
                binding.viewFlipper.displayedChild = 0
            } else {
                binding.viewFlipper.displayedChild = 1
            }
        })
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initViewPagerContent() {
        Utils.initFragment()
        val fm: FragmentManager = supportFragmentManager
        val sa = ViewStateAdapter(fm, lifecycle)
        val pa = findViewById<ViewPager2>(binding.viewPager.id)
        pa.adapter = sa
        val tabLayout = findViewById<TabLayout>(binding.tabLayout.id)
        tabLayout.addTab(tabLayout.newTab().setText("Search"))
        tabLayout.addTab(tabLayout.newTab().setText("Favourites"))
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pa.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        pa.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }

}