package com.example.myapplication.main;

import android.content.Context;

import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainContract {
    public interface View {
        void showProgress();
        void hideProgress();
        void showDataList(List<Team> teamList);
        void showFailureMessage(String msg);
    }

    interface Presenter {
        void getDataListTeams(Context contex);
    }
}
