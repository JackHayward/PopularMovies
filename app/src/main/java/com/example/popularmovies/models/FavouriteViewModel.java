package com.example.popularmovies.models;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.popularmovies.repositories.FavouriteRepository;
import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
  private FavouriteRepository repository;
  private LiveData<List<Favourite>> allFavourites;

  public FavouriteViewModel(@NonNull Application application) {
    super(application);
    repository = new FavouriteRepository(application);
    allFavourites = repository.getAllFavourites();
  }

  //public void nuke() {
  //  repository.nuke();
  //}

  public void insert(Favourite favourite) {
    repository.insert(favourite);
  }

  public void delete(Favourite favourite) {
    repository.delete(favourite);
  }

  public LiveData<List<Favourite>> getAllFavourites() {
    return allFavourites;
  }
}
