//package com.example.browsekittys.network
//
//import androidx.lifecycle.MutableLiveData
//import com.example.browsekittys.network.DataClass.DataResultItem
//import okhttp3.ResponseBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class RetrofitServicesNotWorking {
//    private val size:String = "mid"
//    var imageFound: MutableLiveData<List<DataResultItem>?> = MutableLiveData()
//    fun getResult(): MutableLiveData<List<DataResultItem>?> {
//        return imageFound
//    }
//
//    fun getImages(limit: Int, categoryId: Int) {
//        val retroService = RetrofitInstance.getRetroInstance().create(RetroInterface::class.java)
//        val call = retroService.getImages(limit, categoryId,size)
//        call.enqueue(object : Callback<List<DataResultItem>> {
//            override fun onFailure(call: Call<List<DataResultItem>>, t: Throwable) {
//                imageFound.postValue(null)
//            }
//
//            override fun onResponse(
//                call: Call<List<DataResultItem>>,
//                response: Response<List<DataResultItem>>
//            ) {
//                if (response.isSuccessful && response.body() != null) {
//                    imageFound.value = response.body()!!
//                } else {
//                    imageFound.postValue(null)
//                }
//            }
//        })
//
//    }
//}
