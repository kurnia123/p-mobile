package com.example.myapplication;

import com.example.myapplication.data.TeamLocalFavorite;
import com.example.myapplication.data.TeamRepository;
import com.example.myapplication.data.local.TeamLocalDataSource;
import com.example.myapplication.data.remote.TeamRemoteDataSource;

public class Injection {

    public static TeamRepository provideRepository() {
        return new TeamRepository(new TeamRemoteDataSource());
    }

    public static TeamLocalFavorite provideLocalRepository() {
        return new TeamLocalFavorite(new TeamLocalDataSource());
    }

}
