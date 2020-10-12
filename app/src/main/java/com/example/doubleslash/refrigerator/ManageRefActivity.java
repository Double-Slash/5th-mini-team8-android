package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.autofill.UserData;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.doubleslash.R;
import com.example.doubleslash.data.UserRefData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class ManageRefActivity extends AppCompatActivity implements IngredientAdapter.OnItemClickListener{

    private static final String Tag = "ManageRef";
    private static final String baseurl = "https://0c73c962765a.ngrok.io";

    private Context mContext = this;
    private RecyclerView ingredient_view;
    private IngredientAdapter ingredientAdapter;
    private UserRefData userRefData;

    Retrofit retrofit = new Retrofit.Builder()
            //서버 url
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    NetworkService api = retrofit.create(NetworkService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ref);
        ingredient_view = findViewById(R.id.ingredientView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        ingredient_view.setLayoutManager(layoutManager);
        ingredient_view.addItemDecoration(new ItemDecoration(this));
        ingredientAdapter = new IngredientAdapter(mContext, getIngredientArray());
        ingredientAdapter.setOnItemClickListener(this);
//        updateIng();
        ingredient_view.setAdapter(ingredientAdapter);

        Button what_to_eatBtn = findViewById(R.id.what_to_eatBtn);
        what_to_eatBtn.setOnClickListener(view -> {
            //나중에 수정
//            startActivity(new Intent(getApplicationContext(), AddIngredientActivity.class));
            //finish()로 죽이지 않는다, 뒤로가기로 돌아올 수 있어야 함
        });
    }

    Callback<JsonObject> callback = new Callback<JsonObject>(){
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.e(Tag,"서버통신 성공");
            Log.e(Tag,response.body().toString());
            List<Ingredient> temparray = null;
            JsonObject rootObj = response.body().getAsJsonObject("data");
            if(rootObj != null){
                Gson gson = new GsonBuilder().create();
                TypeToken<List<Ingredient>> typeToken = new TypeToken<List<Ingredient>>(){};
                Type type = typeToken.getType();
                temparray = gson.fromJson(rootObj, type);
            }
            else{
                Log.e(Tag, "failed");
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e(Tag,"Callback onFailure");
        }
    };



//    public ArrayList<Ingredient> updateIng(){
////        ArrayList<Ingredient> arrayList = api.getRefingredient().enqueue(callback);
//
////        }if(call.isSuccessful()){
////            JsonObject body = call.body();
////            JsonParser parser = new JsonParser();
////            assert body != null;
////            Log.e(Tag,body.toString());
//////            for(String name : body.keySet()){
//////
//////            }
//        Log.e(Tag,"냉장고 재료 가져오는 중");
//    }


    //리사이클러뷰 테스트용 하드코딩
    private ArrayList<Ingredient> getIngredientArray() {
        ArrayList<Ingredient> temparray = new ArrayList<>();
        return temparray;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(View view, Ingredient ingredient) {
        Log.e(Tag,"1");
    }
}