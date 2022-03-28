package com.example.browsekittys

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    var limit: MutableLiveData<Int> = MutableLiveData()
    var categoryId: MutableLiveData<Int> = MutableLiveData()
    fun setData(setLimit:Int,setCategoryId:Int){
        limit.value = setLimit
        categoryId.value = setCategoryId
    }

}