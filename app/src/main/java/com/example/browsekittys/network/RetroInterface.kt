package com.example.browsekittys.network

import com.example.browsekittys.network.dataClass.DataResultItem
import retrofit2.Call
import retrofit2.http.*
//@Query("category_ids") category_ids: Int,
interface RetroInterface {
    @Headers("x-api-key: d301744f-a667-41cd-86f1-ecd5fdfa5c3d")
    @GET("search")
    fun getImages(
        @Query("limit") limit: Int?,
        @Query("category_ids") category_ids:Int?
    ): Call<List<DataResultItem>>
}