package com.example.doubleslash.refrigerator;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.doubleslash.R;
import com.example.doubleslash.data.UserRefData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class ManageRefActivity extends AppCompatActivity implements IngredientAdapter.OnItemClickListener{

    private static final String Tag = "ManageRef";
    private static final String baseurl = "https://0c73c962765a.ngrok.io";

    private Context mContext = this;
    private RecyclerView ingredient_view;
    private IngredientAdapter ingredientAdapter;
    private UserRefData userRefData;
    private ArrayList<Ingredient> array = null;

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
        updateIng();
        ingredient_view = findViewById(R.id.ingredientView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        ingredient_view.setLayoutManager(layoutManager);
        ingredient_view.addItemDecoration(new ItemDecoration(this));
        ingredient_view.setAdapter(ingredientAdapter);

        Button what_to_eatBtn = findViewById(R.id.what_to_eatBtn);
        what_to_eatBtn.setOnClickListener(view -> {
            //나중에 수정
//            startActivity(new Intent(getApplicationContext(), AddIngredientActivity.class));
            //finish()로 죽이지 않는다, 뒤로가기로 돌아올 수 있어야 함
        });
    }

    Callback<JsonObject> callback = new Callback<JsonObject>(){
        ArrayList<Ingredient> temparray = new ArrayList<>();
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.e(Tag,"서버통신 성공");
            if(response.body()!=null){
                Log.e(Tag,response.body().toString());
                JsonObject rootObj = response.body().getAsJsonObject("data");
                if(rootObj != null){
                    Gson gson = new GsonBuilder().create();
                    TypeToken<List<Ingredient>> typeToken = new TypeToken<List<Ingredient>>(){};
                    Type type = typeToken.getType();
                    temparray = gson.fromJson(rootObj, type);
                    if(temparray != null){
                        Log.e(Tag,temparray.get(1).getIngredients_name());
                    }
                    temparray.add(temparray.size(), new Ingredient("last_one"));
                    ingredientAdapter = new IngredientAdapter(mContext, temparray);
                }
                else{
                    Log.e(Tag, "failed");
                }
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e(Tag,"Callback onFailure");
        }
    };

    public void updateIng(){
        api.getRefingredient().enqueue(callback);
        Log.e(Tag,"냉장고 재료 가져오는 중");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(View view, Ingredient ingredient) {
        Log.e(Tag,"1");
    }
}