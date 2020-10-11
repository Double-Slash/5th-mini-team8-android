package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.doubleslash.R;
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
    private ArrayList<Ingredient> ingredients;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("냉장고 관리");
        ingredient_view = findViewById(R.id.ingredientView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        ingredient_view.setLayoutManager(layoutManager);
        ingredient_view.addItemDecoration(new ItemDecoration(this));
        ingredientAdapter = new IngredientAdapter(mContext, getIngredientArray());
        ingredientAdapter.setOnItemClickListener(this);
//        updateIng();
        ingredient_view.setAdapter(ingredientAdapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_manage_ref, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.GotoAI){
            startActivity(new Intent(getApplicationContext(), AddIngredientActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    //리사이클러뷰 테스트용 하드코딩
    private ArrayList<Ingredient> getIngredientArray() {
        ArrayList<Ingredient> temparray = new ArrayList<>();
        temparray.add(new Ingredient("누룽지 두부 계란죽", "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00016_2.png"));
        temparray.add(new Ingredient("칼륨 듬뿍 고구마죽", "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00017_2.png"));
        temparray.add(new Ingredient("오렌지 당근펀치", "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00108_2.png"));
        temparray.add(new Ingredient("오색지라시 스시", "http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00009_2.png"));
        Log.e(Tag,"getIngredientArray 2 "+temparray.get(2).getIngredients_name());
        return temparray;
    }

    @Override
    public void onItemClick(View view, Ingredient ingredient) {
        Log.e("RecyclerVIew :: ", ingredient.toString());
    }
}