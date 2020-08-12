package com.example.popularmovies.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Movie implements Serializable {
  @SerializedName("popularity")
  @Expose
  private Double popularity;

  @SerializedName("vote_count")
  @Expose
  private int voteCount;

  @SerializedName("video")
  @Expose
  private boolean video;

  @SerializedName("poster_path")
  @Expose
  private String posterPath;

  @SerializedName("id")
  @Expose
  private int id;

  @SerializedName("adult")
  @Expose
  private boolean adult;

  @SerializedName("backdrop_path")
  @Expose
  private String backdropPath;

  @SerializedName("original_language")
  @Expose
  private String originalLanguage;

  @SerializedName("original_title")
  @Expose
  private String originalTitle;

  @SerializedName("genre_ids")
  @Expose
  private ArrayList<Integer> genreIds = null;

  @SerializedName("language")
  @Expose
  private String language;

  @SerializedName("title")
  @Expose
  private String title;

  @SerializedName("vote_average")
  @Expose
  private Double voteAverage;

  @SerializedName("overview")
  @Expose
  private String overview;

  @SerializedName("release_date")
  @Expose
  private Date releaseDate;

  /**
   * No args constructor for use in serialization
   */
  public Movie() {
  }

  public Movie(Double popularity, int voteCount, boolean video, String posterPath, int id,
      boolean adult, String backdropPath, String originalLanguage, String originalTitle,
      ArrayList<Integer> genreIds, String language, String title, Double voteAverage,
      String overview, Date releaseDate) {
    this.popularity = popularity;
    this.voteCount = voteCount;
    this.video = video;
    this.posterPath = posterPath;
    this.id = id;
    this.adult = adult;
    this.backdropPath = backdropPath;
    this.originalLanguage = originalLanguage;
    this.originalTitle = originalTitle;
    this.genreIds = genreIds;
    this.language = language;
    this.title = title;
    this.voteAverage = voteAverage;
    this.overview = overview;
    this.releaseDate = releaseDate;
  }

  public Double getPopularity() {
    return popularity;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public boolean isVideo() {
    return video;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public int getId() {
    return id;
  }

  public boolean isAdult() {
    return adult;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public ArrayList<Integer> getGenreIds() {
    return genreIds;
  }

  public String getLanguage() {
    return language;
  }

  public String getTitle() {
    return title;
  }

  public Double getVoteAverage() {
    return voteAverage;
  }

  public String getOverview() {
    return overview;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }
}
