package com.example.doubleslash.register;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {
    @POST("/register")
    @FormUrlEncoded()
    Call<JsonObject> register(@Field("id") String id,
                              @Field("name") String name,
                              @Field("email") String email,
                              @Field("password") String password);
}