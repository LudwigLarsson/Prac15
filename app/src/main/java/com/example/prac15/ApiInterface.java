package com.example.prac15;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("manga")
    Call<ArrayList<Anime>> getAnimeList();

    @GET("manga/{id}")
    Call<Anime> getItem(@Path("id") int id);
}
