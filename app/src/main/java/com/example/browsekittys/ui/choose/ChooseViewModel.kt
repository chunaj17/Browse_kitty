package com.example.browsekittys.ui.choose

import androidx.lifecycle.ViewModel

class ChooseViewModel : ViewModel() {
    val categoryItems = listOf("boxes", "clothes", "hats", "sinks", "space", "sunglasses", "ties")
    fun getItems(): MutableList<Int> {
        val items = mutableListOf<Int>()
        for (i in 1..100) {
            items.add(i)
        }
        return items
    }
//  fun findCatImage(limit:Int, categoryId:Int) {
//          RetrofitServices().getImages(limit,categoryId)
//  }
}