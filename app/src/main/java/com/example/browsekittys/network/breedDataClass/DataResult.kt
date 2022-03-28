package com.example.browsekittys.network.breedDataClass

data class DataResult(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)