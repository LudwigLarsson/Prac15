package com.example.prac15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ApiInterface apiInterface;
    FloatingActionButton fab;
    Anime anim;
    int delId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        apiInterface = ServiceBuilder.requestBuilder().create(ApiInterface.class);

        Call<ArrayList<Anime>> getList = apiInterface.getAnimeList();
        /*anim = null;
        delId = -1;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey("item")) {
                String s = extras.getString("item");
                anim = new Gson().fromJson(s, Anime.class);
                Log.d("add", anim.getDetail().toString());
            }
            else if (extras.containsKey("delete")) {
                String s = extras.getString("delete");
                delId = Integer.parseInt(s);
            }
        }*/

        getList.enqueue(new Callback<ArrayList<Anime>>() {
            @Override
            public void onResponse(Call<ArrayList<Anime>> call, Response<ArrayList<Anime>> response) {
                if (response.isSuccessful()){
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    ArrayList<Anime> list = response.body();
                    /*if (anim != null) {
                        list.add(anim);
                    }*/
                    ItemAdapter adapter = new ItemAdapter(MainActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Anime>> call, Throwable t) {

            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}