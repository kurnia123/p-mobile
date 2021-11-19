package com.example.myapplication.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.Team;

@Database(entities = Team.class, version = 1)
public abstract class TeamDatabase extends RoomDatabase {
    public abstract TeamDao teamsDao();

    private static TeamDatabase teamDatabase;

    public static TeamDatabase getTeamDatabase(Context context) {
        synchronized (TeamDatabase.class) {
            if (teamDatabase == null) {
                teamDatabase = Room.databaseBuilder(context, TeamDatabase.class, "db_teams")
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return teamDatabase;
    }

}
