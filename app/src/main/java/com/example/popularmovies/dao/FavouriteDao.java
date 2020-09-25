package com.example.popularmovies.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.popularmovies.models.Favourite;
import java.util.List;

@Dao
public interface FavouriteDao {

  @Insert
  void insert(Favourite favourite);

  @Delete
  void delete(Favourite favourite);

  @Query("SELECT * FROM 'Favourite' ORDER BY 'id' ASC")
  LiveData<List<Favourite>> getAllFavourites();

  //@Query("DELETE FROM Favourite")
  //void nukeTable();
}