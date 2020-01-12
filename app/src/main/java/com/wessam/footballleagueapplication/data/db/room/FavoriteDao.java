package com.wessam.footballleagueapplication.data.db.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insertTeam(FavouriteList favoriteList);

    @Query("select * from favorite_list")
    List<FavouriteList> getFavouriteTeams();

    @Query("SELECT EXISTS (SELECT 1 FROM favorite_list WHERE id = :id)")
    int isFavorite(int id);

    @Delete
    void delete(FavouriteList favoriteList);

}
