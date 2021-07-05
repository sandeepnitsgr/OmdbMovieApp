package com.baghira.omdbmovieapp.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baghira.omdbmovieapp.utils.Utils

class ViewStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)  : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return Utils.getFragment(position)
    }
}