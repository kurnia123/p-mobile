package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "teams")
public class Team implements Parcelable {
    @ColumnInfo(name = "strTeam")
    @SerializedName("strTeam")
    private String name;

    @ColumnInfo(name = "strTeamBadge")
    @SerializedName("strTeamBadge")
    private String photo;

    @ColumnInfo(name = "intFormedYear")
    @SerializedName("intFormedYear")
    private String year;

    @ColumnInfo(name = "strDescriptionEN")
    @SerializedName("strDescriptionEN")
    private String description;

    @PrimaryKey
    @ColumnInfo(name = "idTeam")
    @SerializedName("idTeam")
    private final int idTeam;

    public Team(String name, String photo, String year, String description, int idTeam) {
        this.name = name;
        this.photo = photo;
        this.idTeam = idTeam;
        this.description = description;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeString(this.description);
        dest.writeString(this.year);
        dest.writeInt(this.idTeam);
    }

    private Team(Parcel in) {
        this.name = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
        this.year = in.readString();
        this.idTeam = in.readInt();
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {

        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}
