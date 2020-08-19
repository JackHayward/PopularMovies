package com.example.popularmovies;

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
  private static String baseUrl = "http://api.themoviedb.org";
  private static final String language = "en-US";
  private static final int page = 1;
  private static final String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
  private static final String category = "popular";

  public MainActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new GridLayoutManager(this, 3);

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