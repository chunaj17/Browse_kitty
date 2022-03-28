//package com.example.browsekittys.network
//
//import com.example.browsekittys.network.DataClass.DataResultItem
//import com.google.gson.JsonObject
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import com.google.gson.Gson
//import com.mad.driver.firebaseutils.FirebaseCrash
//import com.mad.driver.model.AppResponse
//import com.mad.driver.model.AppResult
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import okhttp3.ResponseBody
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import timber.log.Timber
//
//private val TAG = "RetroUtils"
//
//    @ExperimentalCoroutinesApi
//    fun Call<JsonObject>.doEnqueue(instance: RetrofitInstance): Callback<List<DataResultItem>> =
//            try {
//                this.trySend(AppResult.InProgress).isSuccess
//                this@doEnqueue.enqueue(object :
//                    Callback<JsonObject> {
//                    override fun onResponse(
//                        call: Call<JsonObject>,
//                        response: Response<JsonObject>,
//                    ) {
//                        try {
//
//                            if (response.isSuccessful) {
//                                val convertedResponse =
//                                    convertResponse(response.body(), true, null)
//                                if (convertedResponse != null) {
//                                    this@callbackFlow.trySend(AppResult.Success(convertedResponse)).isSuccess
//
//                                } else {
//                                    this@callbackFlow.trySend(
//                                        AppResult.Failure(
//                                            Throwable("error"),
//                                            "null response",
//                                            null
//                                        )
//                                    ).isSuccess
//                                }
//                            } else {
//                                val errorResponse: ResponseBody? = response.errorBody()
//                                val errorConverted = convertResponse(null, false, errorResponse)
//                                if (errorConverted != null) {
//                                    this@callbackFlow.trySend(
//                                        AppResult.Failure(
//                                            errorConverted.toThrowable(),
//                                            errorConverted.message.toString(),
//                                            errorConverted.errors
//                                        )
//                                    ).isSuccess
//
//                                } else {
//                                    this@callbackFlow.trySend(
//                                        AppResult.Error.NonRecoverableError(
//                                            Exception(
//                                                "Unknown Error"
//                                            )
//                                        )
//                                    ).isSuccess
//                                }
//                                Timber.d("onResponse: ${errorConverted}")
//                            }
//                        } catch (e: Exception) {
//                            firebaseCrash.setErrorToFireBase(e, "onResponse RetroUtils.kt  65: ")
//
//                            close(e)
//
//                        }
//                    }
//
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        firebaseCrash.setErrorToFireBase(t, "onFailure RetroUtils.kt  76: ")
//
//                        close(t)
//
//                    }
//                })
//            } catch (e: Exception) {
//                firebaseCrash.setErrorToFireBase(e, "doEnqueue RetroUtils.kt  85: ")
//                close(e)
//            }
//
//            awaitClose { }
//         as Flow<AppResult<AppResponse>>
//
//    fun convertResponse(
//        _response: JsonObject? = null,
//        type: Boolean,
//        error: ResponseBody? = null,
//    ): AppResponse? {
//        val gson = Gson()
//
//
//
//
//        return if (type) {
//            try {
//                _response?.let { gson.fromJson(it, AppResponse::class.java) }
//            } catch (e: Exception) {
//                null
//            }
//        } else {
//            try {
//                error?.let {
//                    gson.fromJson(
//                        it.charStream(),
//                        AppResponse::class.java
//                    )
//                }
//            } catch (e: Exception) {
//                null
//            }
//
//        }
//    }