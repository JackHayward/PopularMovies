package com.example.popularmovies.api;

import com.example.popularmovies.models.Movie;
import com.example.popularmovies.models.MovieReview;
import com.example.popularmovies.models.MovieTrailer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {
  @GET("/3/movie/{category}")
  Call<Movie> getMovies(
      @Path("category") String category,
      @Query("api_key") String apiKey,
      @Query("language") String language,
      @Query("page") int page
  );

  @GET("/3/movie/{id}/videos")
  Call<MovieTrailer> getTrailers(
      @Path("id") String id,
      @Query("api_key") String apiKey
  );

  @GET("/3/movie/{id}/reviews")
  Call<MovieReview> getReviews(
      @Path("id") String id,
      @Query("api_key") String apiKey,
      @Query("language") String language,
      @Query("page") int page
  );
}
