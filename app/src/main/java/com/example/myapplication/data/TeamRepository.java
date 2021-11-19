package com.example.myapplication.data;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.data.remote.TeamRemoteDataSource;
import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements TeamDataSource {

    private final TeamRemoteDataSource remoteDataSource;

    public TeamRepository(TeamRemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getListTeams(Context context, GetListItemCallback callback) {
        Toast.makeText(context, "Use API database", Toast.LENGTH_SHORT).show();
        remoteDataSource.getListTeams(context, new GetListItemCallback() {
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
