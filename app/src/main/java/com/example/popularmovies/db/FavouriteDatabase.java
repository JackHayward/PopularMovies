package com.example.popularmovies.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.popularmovies.dao.FavouriteDao;
import com.example.popularmovies.models.Favourite;

@Database(entities = { Favourite.class }, version = 2, exportSchema = false)
public abstract class FavouriteDatabase extends RoomDatabase {
  public abstract FavouriteDao favouriteDao();

  private static FavouriteDatabase INSTANCE;

  public static synchronized FavouriteDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
          FavouriteDatabase.class, "favourite-database").fallbackToDestructiveMigration().build();
    }
    return INSTANCE;
  }

  public static void destroyInstance() {
    INSTANCE = null;
  }
}
