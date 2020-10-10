package com.example.doubleslash

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {
    @POST("/api/register")
    @FormUrlEncoded
    fun signup(@Field("email") email : String,
               @Field("password") password : String,
               @Field("name") name : String
    ): Call<DataModel.SignUpResponse>
}