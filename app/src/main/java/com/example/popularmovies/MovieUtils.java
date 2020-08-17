package com.example.popularmovies;

import android.widget.TextView;
import com.example.popularmovies.models.MovieResults;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieUtils {
  private static String baseUrl = "http://api.themoviedb.org";
  private static final String language = "en-US";
  private static final int page = 1;
  private static final String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
  private static final String category = "popular";

  Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
  MoviesApi moviesApi = retrofit.create(MoviesApi.class);
  Call<MovieResults> call = moviesApi.getMovies(category, apiKey, language, page);

  public void applyMovieListToView(TextView textView) {
    call.enqueue(new Callback<MovieResults>() {
      @Override public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
        MovieResults movieResults = response.body();
        assert movieResults != null;
        List<MovieResults.ResultsBean> movieList = movieResults.getResults();

        MovieResults.ResultsBean movie = movieList.get(0);
        textView.setText(movie.getTitle());
      }

      @Override public void onFailure(Call<MovieResults> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}