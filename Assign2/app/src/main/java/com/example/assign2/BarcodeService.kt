package com.example.assign2

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BarcodeService {

    @GET("api/acba30d26cbd425e9014/C005/json/1/5/BAR_CD={BAR_CD}")
    fun getFoodByBarcode(
        @Path("BAR_CD") barcode: String
    ): Call<Barcode>
//
//    @POST("URL")
//    fun get_data(@Header("Authorization") token: String, @Body(..)...)
}