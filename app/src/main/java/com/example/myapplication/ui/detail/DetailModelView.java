package com.example.myapplication.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.Team;

public class DetailModelView extends ViewModel {
    private MutableLiveData<Team>  teams = new MutableLiveData<>();

    public LiveData<Team> getTeam() {
        return teams;
    }

    public void setTeam(Team team) {
        teams.setValue(team);
    }
}
