package com.wessam.footballleagueapplication.data.db.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_list")
public class FavouriteList {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "team_logo")
    private String crestUrl;

    @ColumnInfo(name = "team_name")
    private String name;

    @ColumnInfo(name = "team_short_name")
    private String shortName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
