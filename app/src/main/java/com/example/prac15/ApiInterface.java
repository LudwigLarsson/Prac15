package com.example.prac15;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("manga")
    Call<ArrayList<Anime>> getAnimeList();

}
