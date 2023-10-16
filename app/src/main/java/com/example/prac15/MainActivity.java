package com.example.prac15;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        apiInterface = ServiceBuilder.requestBuilder().create(ApiInterface.class);

        Call<ArrayList<Anime>> getList = apiInterface.getAnimeList();

        getList.enqueue(new Callback<ArrayList<Anime>>() {
            @Override
            public void onResponse(Call<ArrayList<Anime>> call, Response<ArrayList<Anime>> response) {
                if (response.isSuccessful()){
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setHasFixedSize(true);
                    ArrayList<Anime> list = response.body();
                    ItemAdapter adapter = new ItemAdapter(MainActivity.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Anime>> call, Throwable t) {

            }
        });
    }
}