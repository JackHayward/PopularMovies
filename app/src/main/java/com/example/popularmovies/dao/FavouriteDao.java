package com.example.popularmovies.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.popularmovies.models.Favourite;
import java.util.List;

@Dao
public interface FavouriteDao {
  @Insert
  Long insert(Favourite favourite);

  @Query("SELECT * FROM 'Favourite' ORDER BY 'id' ASC")
  List<Favourite> getAllFavourites();

  @Query("SELECT * FROM 'Favourite' WHERE 'id' = :id")
  List<Favourite> checkIfFavouriteExists(String id);

  @Delete
  void delete(Favourite favourite);
}
