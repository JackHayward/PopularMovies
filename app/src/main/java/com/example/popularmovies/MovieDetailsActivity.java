package com.example.popularmovies;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.adapters.MovieTrailerAdapter;
import com.example.popularmovies.api.MoviesApi;
import com.example.popularmovies.models.MovieTrailer;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private TextView title,releaseDate,voteAverage,synopsis;
  private ImageView image;
  private static String imageBaseUrl = "http://image.tmdb.org/t/p/w185/";
  private static String baseUrl = "http://api.themoviedb.org";
  private static final String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
  private String movieId = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);

    title = (TextView) findViewById(R.id.movie_title);
    synopsis = (TextView) findViewById(R.id.synopsis);
    releaseDate = (TextView) findViewById(R.id.release_date);
    voteAverage = (TextView) findViewById(R.id.average_rating);
    image = (ImageView) findViewById(R.id.movie_thumbnail);

    Intent intent = getIntent();
    String intentTitle = intent.getExtras().getString("Title");
    String intentReleaseDate = intent.getExtras().getString("ReleaseDate");
    String intentVoteAverage = intent.getExtras().getString("VoteAverage");
    String intentSynopsis = intent.getExtras().getString("Synopsis");
    String intentImage = intent.getExtras().getString("Image");
    movieId = intent.getExtras().getString("Id");

    title.setText(intentTitle);
    synopsis.setText(intentSynopsis);
    releaseDate.setText(intentReleaseDate);
    voteAverage.setText(intentVoteAverage);
    Picasso.get().load(imageBaseUrl + intentImage).into(image);
    loadTrailers();
  }

  private void loadTrailers() {
    recyclerView = findViewById(R.id.trailerRecyclerView);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    MoviesApi moviesApi = retrofit.create(MoviesApi.class);
    Call<MovieTrailer> call = moviesApi.getTrailers(movieId, apiKey);

    ArrayList<MovieTrailer.ResultsBean> movieTrailers = new ArrayList<>();

    call.enqueue(new Callback<MovieTrailer>() {
      @Override public void onResponse(Call<MovieTrailer> call, Response<MovieTrailer> response) {
        MovieTrailer movieTrailer = response.body();
        assert movieTrailer != null;
        List<MovieTrailer.ResultsBean> parsedMovieTrailersList = movieTrailer.getResults();

        movieTrailers.addAll(parsedMovieTrailersList);

        adapter = new MovieTrailerAdapter(movieTrailers);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
      }

      @Override public void onFailure(Call<MovieTrailer> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}