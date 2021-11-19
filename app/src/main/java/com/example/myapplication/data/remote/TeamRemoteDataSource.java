package com.example.myapplication.data.remote;

import android.content.Context;

import com.example.myapplication.adapter.Contract;
import com.example.myapplication.data.TeamDataSource;
import com.example.myapplication.model.ResultTeam;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamRemoteDataSource implements TeamDataSource {

    public final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListTeams(Context context, GetListItemCallback callback) {
        Call<ResultTeam> call = apiInterface.getAllTeam(Contract.s, Contract.c);
        call.enqueue(new Callback<ResultTeam>() {
            @Override
            public void onResponse(Call<ResultTeam> call, Response<ResultTeam> response) {
                if (response.body() != null) {
                    callback.onSuccess(response.body().getTeams());
                }
            }

            @Override
            public void onFailure(Call<ResultTeam> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
