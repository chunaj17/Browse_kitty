package com.example.browsekittys.ui.pets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.browsekittys.network.RetrofitInstance
import com.example.browsekittys.network.dataClass.DataResultItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetsViewModel(/*args: PetsArgs*/) : ViewModel() {
    var imageFound: MutableLiveData<List<DataResultItem>?> = MutableLiveData()
    fun getResult(): MutableLiveData<List<DataResultItem>?> {
        return imageFound
    }

    fun getImages(limit: Int?, cateGoryId:Int?)  {

        val retroService = RetrofitInstance.getRetroInstance()
        val call = retroService.getImages(limit,cateGoryId)
      return call.enqueue(object : Callback<List<DataResultItem>> {
            override fun onFailure(call: Call<List<DataResultItem>>, t: Throwable) {
                imageFound.postValue(null)
            }
            override fun onResponse(
                call: Call<List<DataResultItem>>,
                response: Response<List<DataResultItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    imageFound.postValue(response.body())
                } else {
                    imageFound.postValue(null)
                }
            }
        })

    }
}