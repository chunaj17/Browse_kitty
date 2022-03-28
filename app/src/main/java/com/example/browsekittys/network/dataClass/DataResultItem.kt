package com.example.browsekittys.network.dataClass
data class DataResultItem(
    val breeds: List<Any>,
    val categories: List<Category>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)