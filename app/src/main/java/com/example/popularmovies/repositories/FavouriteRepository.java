package com.example.popularmovies.repositories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import com.example.popularmovies.dao.FavouriteDao;
import com.example.popularmovies.db.FavouriteDatabase;
import com.example.popularmovies.models.Favourite;
import java.util.List;

public class FavouriteRepository {
  private FavouriteDao favouriteDao;
  private LiveData<List<Favourite>> allFavourites;

  public FavouriteRepository(Application application) {
    FavouriteDatabase database = FavouriteDatabase.getInstance(application);
    favouriteDao = database.favouriteDao();
    allFavourites = favouriteDao.getAllFavourites();
  }

  //public void nuke() {
  //  new NukeAsyncTask(favouriteDao).execute();
  //}

  public void insert(Favourite favourite) {
    new InsertFavouriteAsyncTask(favouriteDao).execute(favourite);
  }

  public void delete(Favourite favourite) {
    new DeleteFavouriteAsyncTask(favouriteDao).execute(favourite);
  }

  public LiveData<List<Favourite>> getAllFavourites() {
    return allFavourites;
  }

  private static class InsertFavouriteAsyncTask extends AsyncTask<Favourite, Void, Void> {
    private FavouriteDao favouriteDao;

    private InsertFavouriteAsyncTask (FavouriteDao favouriteDao) {
      this.favouriteDao = favouriteDao;
    }

    @Override protected Void doInBackground(Favourite... favourites) {
      favouriteDao.insert(favourites[0]);
      return null;
    }
  }

  private static class DeleteFavouriteAsyncTask extends AsyncTask<Favourite, Void, Void> {
    private FavouriteDao favouriteDao;

    private DeleteFavouriteAsyncTask (FavouriteDao favouriteDao) {
      this.favouriteDao = favouriteDao;
    }

    @Override protected Void doInBackground(Favourite... favourites) {
      favouriteDao.delete(favourites[0]);
      return null;
    }
  }

  //private static class NukeAsyncTask extends AsyncTask<Void, Void, Void> {
  //  private FavouriteDao favouriteDao;
  //
  //  private NukeAsyncTask (FavouriteDao favouriteDao) {
  //    this.favouriteDao = favouriteDao;
  //  }
  //
  //  @Override protected Void doInBackground(Void... voids) {
  //    favouriteDao.nukeTable();
  //    return null;
  //  }
  //}
}
