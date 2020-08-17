package com.example.popularmovies;

import com.example.popularmovies.models.MovieResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
  @GET("/3/movie/{category}")
  Call<MovieResults> getMovies(
      @Path("category") String category,
      @Query("api_key") String apiKey,
      @Query("language") String language,
      @Query("page") int page
  );
}
