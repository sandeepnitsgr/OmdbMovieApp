package com.baghira.omdbmovieapp.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.databinding.MovieDetailBinding

class MovieDetailActivity : AppCompatActivity(){
    private lateinit var binding: MovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.movie_detail)
        binding.apply{
            year.text = year.text.toString() + intent.getIntExtra("sandeep", -1).toString()
        }
    }

}
