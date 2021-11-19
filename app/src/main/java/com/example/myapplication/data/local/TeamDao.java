package com.example.myapplication.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Team;

import java.util.List;

@Dao
public interface TeamDao {
    @Insert
    void insert(Team teamList);

    @Query("SELECT * FROM teams")
    List<Team> select();

    @Delete
    void delete(Team team);

    @Update
    void update(Team team);
}
