package com.example.doubleslash.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.doubleslash.R;
import com.example.doubleslash.ui.login.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String Tag = "register";
    private static final String baseurl = "http://ec2-13-209-4-72.ap-northeast-2.compute.amazonaws.com:3000";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    NetworkService api = retrofit.create(NetworkService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText usernameEditText = findViewById(R.id.name);
        final EditText emailEditText = findViewById(R.id.email);
        final EditText idEditText = findViewById(R.id.user_id);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button signUp_completedButton = findViewById(R.id.signUp_completed);


        signUp_completedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("register", "here");

                api.register(idEditText.getText().toString(),
                        usernameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        passwordEditText.getText().toString()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body()!=null){
                            Log.e("register", "response 33: "+new Gson().toJson(response.body().toString()) );
                        }
                        else{
                            Log.e(Tag, "failed");
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("TAG", "onFailure: "+t.toString() );
                    }
                });

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }



}