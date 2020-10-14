package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubleslash.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddIngredientActivity extends AppCompatActivity {

    private static final String Tag = "AddIngredientActivity";
    private static final String baseurl = "http://ec2-13-209-4-72.ap-northeast-2.compute.amazonaws.com:3000";

    private Context mContext = this;
    private RecyclerView addIngredientView;
    private SearchIngredientAdapter searchIngredientAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            //서버 url
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    NetworkService api = retrofit.create(NetworkService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        RecyclerViewDecoration spaceDecoration = new RecyclerViewDecoration(30);

        addIngredientView = findViewById(R.id.addIngredientView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        addIngredientView.setLayoutManager(layoutManager);
        addIngredientView.addItemDecoration(spaceDecoration);

        EditText search_editText = findViewById(R.id.search_editText);
        Button search_button = findViewById(R.id.search_button);
        Button ingredient_addBt = findViewById(R.id.ingredient_addBt);
        Button slidebtn = findViewById(R.id.slidebtn);
        ConstraintLayout ref_container = findViewById(R.id.ref_container);

        search_button.setOnClickListener(view -> {
            //서버통신으로 String 보내고 리스트 받아오기
            api.getSearchedResult(search_editText.getText().toString()).enqueue(searchCallback);
        });
        ingredient_addBt.setOnClickListener(view -> {

            //slidelist로 보내기
        });

        slidebtn.setOnClickListener(view -> {
            if(ref_container.getVisibility() == View.GONE){
                Log.e(Tag,"1");
                ref_container.setVisibility(View.VISIBLE);
            }
            else{
                Log.e(Tag,"2");
                ref_container.setVisibility(View.GONE);
            }
        });
    }

    Callback<JsonObject> searchCallback = new Callback<JsonObject>() {
        List<Ingredients> temparray = new ArrayList<>();
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if(response.body()!=null){
                Log.e(Tag,response.body().toString());
                JsonArray rootObj = response.body().getAsJsonArray("data");
                if(rootObj != null){
                    Log.e(Tag,"rootObj:"+ rootObj.get(0).toString());
                    Gson gson = new GsonBuilder().create();
                    TypeToken<List<Ingredients>> typeToken = new TypeToken<List<Ingredients>>(){};
                    Type type = typeToken.getType();
                    temparray = gson.fromJson(rootObj.toString(), type);
//                    temparray = gson.fromJson(rootObj, Ingredient.class);
                    if(temparray != null){
                        Log.e(Tag, "ji"+temparray.get(1).getIngredient_name()+"\n"+temparray.get(0).getIngredient_name());
                        searchIngredientAdapter = new SearchIngredientAdapter(mContext, temparray);
                        Log.e(Tag,"search:"+searchIngredientAdapter.getItemCount());
                        addIngredientView.setAdapter(searchIngredientAdapter);
                    }
                }
                else{
                    Log.e(Tag, "failed");
                }
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {

        }
    };
}