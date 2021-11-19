package com.example.myapplication.data.remote;

import com.example.myapplication.model.ResultTeam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/v1/json/1/search_all_teams.php")
    Call<ResultTeam> getAllTeam(
            @Query("s") String s,
            @Query("c") String c
    );
}
