package com.baghira.omdbmovieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data")
    val data : Int?,
    val isLiked: Boolean = false
)
