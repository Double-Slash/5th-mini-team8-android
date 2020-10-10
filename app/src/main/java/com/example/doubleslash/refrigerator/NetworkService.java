package com.example.doubleslash.refrigerator;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkService {

    @GET("/ref")
    Call<JsonObject> getRefingredient(@Field("userId") String userId);
}
