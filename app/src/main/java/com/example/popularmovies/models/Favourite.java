package com.example.popularmovies.models;

import android.provider.BaseColumns;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favourite {

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "movie_id")
  private String movieId;

  @ColumnInfo(name = "movie_title")
  private String movieTitle;

  @ColumnInfo(name = "user_rating")
  private String userRating;

  @ColumnInfo(name = "poster_path")
  private String posterPath;

  @ColumnInfo(name = "synopsis")
  private String synopsis;

  public Favourite(String movieId, String movieTitle, String userRating, String posterPath,
      String synopsis) {
    this.movieId = movieId;
    this.movieTitle = movieTitle;
    this.userRating = userRating;
    this.posterPath = posterPath;
    this.synopsis = synopsis;
  }

  public int getId() {
    return id;
  }

  public String getMovieId() {
    return movieId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public String getUserRating() {
    return userRating;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public void setUserRating(String userRating) {
    this.userRating = userRating;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }
}
