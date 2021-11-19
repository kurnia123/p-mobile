package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultTeam {
    @SerializedName("teams")
    private ArrayList<Team> teams;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
