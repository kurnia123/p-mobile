package com.example.myapplication.main;

import android.content.Context;

import com.example.myapplication.data.TeamDataSource;
import com.example.myapplication.data.TeamRepository;
import com.example.myapplication.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final TeamRepository repository;
    private final MainContract.View view;

    public MainPresenter(TeamRepository repository, MainContract.View view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context contex) {
        view.showProgress();
        repository.getListTeams(contex, new TeamDataSource.GetListItemCallback() {
            @Override
            public void onSuccess(List<Team> data) {
                view.hideProgress();
                view.showDataList(data);
            }

            @Override
            public void onFailed(String errorMessage) {
                view.hideProgress();
                view.showFailureMessage(errorMessage);
            }
        });
    }
}
