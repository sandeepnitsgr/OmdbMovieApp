package com.baghira.omdbmovieapp.utils

import androidx.fragment.app.Fragment
import com.baghira.omdbmovieapp.presentation.ui.fragment.FavouriteFragment
import com.baghira.omdbmovieapp.presentation.ui.fragment.SearchFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Utils {

    private val map =HashMap<Int, Fragment>()

    fun initFragment(){
        map[0] = SearchFragment.newInstance()
        map[1] = FavouriteFragment.newInstance()
    }

    fun getFragment(position:Int): Fragment {
        return map[position]!!
    }
}
