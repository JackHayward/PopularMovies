package com.example.popularmovies;

import com.example.popularmovies.models.Movie;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesApi {
  @GET("?sort_by=popularity.desc&api_key=1c0e9c5248204984a257ab3dd013c1a1")
  Call<List<Movie>> getMovies();
}
