package com.example.popularmovies;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.models.MovieResults;
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

  public MainActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    loadPage(1);
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
    }

    loadPage(1);
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
    }

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    MoviesApi moviesApi = retrofit.create(MoviesApi.class);
    Call<MovieResults> call = moviesApi.getMovies(category, apiKey, language, page);

    ArrayList<MovieResults.ResultsBean> movies = new ArrayList<>();

    call.enqueue(new Callback<MovieResults>() {
      @Override public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
        MovieResults movieResults = response.body();
        assert movieResults != null;
        List<MovieResults.ResultsBean> parsedMovieList = movieResults.getResults();

        movies.addAll(parsedMovieList);

        adapter = new MovieAdapter(movies);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
      }

      @Override public void onFailure(Call<MovieResults> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}