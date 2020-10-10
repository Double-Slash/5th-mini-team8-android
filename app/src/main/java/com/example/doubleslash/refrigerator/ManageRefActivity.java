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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageRefActivity extends AppCompatActivity implements IngredientAdapter.OnItemClickListener{

    private static final String Tag = "ManageRef";

    private Context mContext = this;
    private RecyclerView ingredient_view;
    private IngredientAdapter ingredientAdapter;
    private ArrayList<Ingredient> ingredients;
    private String baseUrl = "https://84b616f25c2f.ngrok.io";

    Retrofit retrofit = new Retrofit.Builder()
            //서버 url
            .baseUrl(baseUrl)
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
        ingredient_view.setAdapter(ingredientAdapter);
    }

    public void updateIng() throws IOException {
        ArrayList<Ingredient> temparray;
        Response<JsonObject> call = api.getRefingredient("kai9702").execute();
        if(call.isSuccessful()){
            Log.e(Tag,"서버통신 성공");
            JsonObject body = call.body();
            JsonParser parser = new JsonParser();
            assert body != null;
            Log.e(Tag,body.toString());
//            for(String name : body.keySet()){
//
//            }
        }
        Log.e(Tag,"냉장고 재료 가져오는 중");
    }

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