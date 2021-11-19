package com.example.myapplication.data;

import android.content.Context;

import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public interface TeamDataSource {
    void getListTeams(Context context, GetListItemCallback callback);

    interface GetListItemCallback {
        void onSuccess(List<Team> data);
        void onFailed(String errorMessage);
    }
}
