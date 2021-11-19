package com.example.myapplication.main;

import android.content.Context;

import com.example.myapplication.data.TeamDataSource;
import com.example.myapplication.data.TeamLocalFavorite;
import com.example.myapplication.model.Team;

import java.util.List;

public class FavoritePresenter implements MainContract.Presenter {

    private final TeamLocalFavorite teamLocalFavorite;
    private final MainContract.View view;

    public FavoritePresenter(TeamLocalFavorite teamLocalFavorite, MainContract.View view) {
        this.teamLocalFavorite = teamLocalFavorite;
        this.view = view;
    }

    @Override
    public void getDataListTeams(Context contex) {
        view.showProgress();
        teamLocalFavorite.getListTeams(contex, new TeamDataSource.GetListItemCallback() {
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
