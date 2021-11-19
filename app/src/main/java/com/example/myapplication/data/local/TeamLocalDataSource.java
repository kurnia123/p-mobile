package com.example.myapplication.data.local;

import android.content.Context;

import com.example.myapplication.data.TeamDataSource;

public class TeamLocalDataSource implements TeamDataSource {

    @Override
    public void getListTeams(Context context, GetListItemCallback callback) {
        TeamDatabase teamDatabase = TeamDatabase.getTeamDatabase(context);
        if (teamDatabase.teamsDao().select() != null) {
            callback.onSuccess(teamDatabase.teamsDao().select());
        } else {
            callback.onFailed("local database kosong");
        }
    }
}
