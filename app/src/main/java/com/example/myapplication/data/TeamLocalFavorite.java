package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.data.local.TeamLocalDataSource;
import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamLocalFavorite implements TeamDataSource {

    private final TeamLocalDataSource localDataSource;

    public TeamLocalFavorite(TeamLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void getListTeams(Context context, GetListItemCallback callback) {
        localDataSource.getListTeams(context, new GetListItemCallback() {
            @Override
            public void onSuccess(List<Team> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailed(String errorMessage) {
                callback.onFailed(errorMessage);
            }
        });
    }
}
