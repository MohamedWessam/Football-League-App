package com.wessam.footballleagueapplication.data.db.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wessam.footballleagueapplication.utils.Constants;

@Database(entities = {FavouriteList.class}, version = 2, exportSchema = false)
public abstract class FavouriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();

    private static volatile FavouriteDatabase INSTANCE;

    public static FavouriteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavouriteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavouriteDatabase.class, Constants.APP_DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}