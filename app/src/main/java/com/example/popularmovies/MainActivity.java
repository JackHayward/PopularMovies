package com.example.popularmovies;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.adapters.MovieAdapter;
import com.example.popularmovies.api.MoviesApi;
import com.example.popularmovies.models.Favourite;
import com.example.popularmovies.models.FavouriteViewModel;
import com.example.popularmovies.models.Movie;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private static int currentSortMode = 1;
  private static String baseUrl = "http://api.themoviedb.org";
  private static final String language = "en-US";
  private static final int page = 1;
  private static final String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
  private static String category = "popular";
  private FavouriteViewModel favouriteViewModel;
  List<Favourite> favouriteMovies = new ArrayList<>();

  public MainActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState != null) {
      currentSortMode = savedInstanceState.getInt("sort");
    }

    favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
    favouriteViewModel.getAllFavourites().observe(this, new Observer<List<Favourite>>() {
      @Override public void onChanged(List<Favourite> favourites) {
        favouriteMovies =
            favouriteViewModel.getAllFavourites().getValue();

        if (currentSortMode == 3) {
          loadFavourites();
        }
      }
    });

    switch (currentSortMode) {
      case 1:
        loadPage(1);
        break;
      case 2:
        loadPage(1);
        break;
      case 3:
        loadFavourites();
        break;
    }

  }

  @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
    outState.putInt("sort", currentSortMode);
    super.onSaveInstanceState(outState);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.sort_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.sort_by_popularity:
        currentSortMode = 1;
        break;
      case R.id.sort_by_top:
        currentSortMode = 2;
        break;
      case R.id.favourites:
        currentSortMode = 3;
        loadFavourites();
        break;
    }

    if (currentSortMode == 1 || currentSortMode == 2) {
      loadPage(1);
    }
    return super.onOptionsItemSelected(item);
  }

  private void loadPage(final int page) {
    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new GridLayoutManager(this, 3);

    switch(currentSortMode) {
      case 1:
        category = "popular";
        break;
      case 2:
        category = "top_rated";
        break;
      case 3:
        loadFavourites();
        break;
    }

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    MoviesApi moviesApi = retrofit.create(MoviesApi.class);
    Call<Movie> call = moviesApi.getMovies(category, apiKey, language, page);

    ArrayList<Movie.ResultsBean> movies = new ArrayList<>();

    call.enqueue(new Callback<Movie>() {
      @Override public void onResponse(Call<Movie> call, Response<Movie> response) {
        Movie movie = response.body();
        assert movie != null;
        List<Movie.ResultsBean> parsedMovieList = movie.getResults();

        movies.addAll(parsedMovieList);

        adapter = new MovieAdapter(movies);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
      }

      @Override public void onFailure(Call<Movie> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }

  private void loadFavourites() {
    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new GridLayoutManager(this, 3);
    favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
    ArrayList<Movie.ResultsBean> finalMovieList = new ArrayList<>();

    favouriteMovies =
        favouriteViewModel.getAllFavourites().getValue();

    for (Favourite favouriteItem : favouriteMovies) {
      Movie.ResultsBean movie = new Movie.ResultsBean();
      movie.setId(Integer.parseInt(favouriteItem.getMovieId()));
      movie.setTitle(favouriteItem.getMovieTitle());
      movie.setVote_average(Double.parseDouble(favouriteItem.getUserRating()));
      movie.setPoster_path(favouriteItem.getPosterPath());
      movie.setOverview(favouriteItem.getSynopsis());

      finalMovieList.add(movie);
    }
    
    adapter = new MovieAdapter(finalMovieList);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
  }
}