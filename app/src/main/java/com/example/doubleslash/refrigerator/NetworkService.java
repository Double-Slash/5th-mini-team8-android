package com.example.doubleslash.refrigerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkService {
    @GET("/ref")
    @Headers("authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFhYSIsImlhdCI6MTYwMjMyMDg5Mn0.TMsF23gELkCIBBy024OmuPEgQ73pe6Pz19l2mv-bqS4")
    Call<JsonObject> getRefingredient();

    @POST("/ref/deleteIn")
    @FormUrlEncoded
    @Headers("authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFhYSIsImlhdCI6MTYwMjMyMDg5Mn0.TMsF23gELkCIBBy024OmuPEgQ73pe6Pz19l2mv-bqS4")
    Call<JsonObject> deleteRefingredient(@Field("ingredient") String string);

    @POST("/ref/searchIn")
    @FormUrlEncoded
    @Headers("authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImFhYSIsImlhdCI6MTYwMjMyMDg5Mn0.TMsF23gELkCIBBy024OmuPEgQ73pe6Pz19l2mv-bqS4")
    Call<JsonObject> getSearchedResult(@Field("ingredient") String name);
}
