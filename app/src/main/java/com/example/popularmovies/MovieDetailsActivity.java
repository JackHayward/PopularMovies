package com.example.popularmovies;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.adapters.MovieReviewAdapter;
import com.example.popularmovies.adapters.MovieTrailerAdapter;
import com.example.popularmovies.api.MoviesApi;
import com.example.popularmovies.dao.FavouriteDao;
import com.example.popularmovies.db.FavouriteDatabase;
import com.example.popularmovies.models.Favourite;
import com.example.popularmovies.models.FavouriteViewModel;
import com.example.popularmovies.models.MovieReview;
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
  private RecyclerView trailerRecyclerView;
  private RecyclerView reviewRecyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager verticalLayoutManager;
  private RecyclerView.LayoutManager horizontalLayoutManager;
  private TextView title, releaseDate, voteAverage, synopsis;
  private Button favouriteButton;
  private ImageView image;
  private static String imageBaseUrl = "http://image.tmdb.org/t/p/w185/";
  private static String baseUrl = "http://api.themoviedb.org";
  private static final String apiKey = "1c0e9c5248204984a257ab3dd013c1a1";
  private String movieId = "";
  private static final String language = "en-US";
  private static final int page = 1;
  private FavouriteViewModel favouriteViewModel;
  private List<Favourite> favouriteList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_details);

    title = (TextView) findViewById(R.id.movie_title);
    synopsis = (TextView) findViewById(R.id.synopsis);
    releaseDate = (TextView) findViewById(R.id.release_date);
    voteAverage = (TextView) findViewById(R.id.average_rating);
    image = (ImageView) findViewById(R.id.movie_thumbnail);
    favouriteButton = (Button) findViewById(R.id.favourite);
    Intent intent = getIntent();
    String intentTitle = intent.getExtras().getString("Title");
    String intentReleaseDate = intent.getExtras().getString("ReleaseDate");
    String intentVoteAverage = intent.getExtras().getString("VoteAverage");
    String intentSynopsis = intent.getExtras().getString("Synopsis");
    String intentImage = intent.getExtras().getString("Image");
    movieId = intent.getExtras().getString("Id");

    favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);

    favouriteViewModel.getAllFavourites().observe(this, favourites -> {
      favouriteList = favourites;
      favouriteButton.setText(R.string.Favourite);
      if (favourites.size() > 0) {
        for (Favourite favourite : favourites) {
          if (favourite.getMovieId().equals(movieId)) {
            favouriteButton.setText(R.string.Unfavourite);
          }
        }
      } else {
        favouriteButton.setText(R.string.Favourite);
      }
    });

    title.setText(intentTitle);
    synopsis.setText(intentSynopsis);
    releaseDate.setText(intentReleaseDate);
    voteAverage.setText(intentVoteAverage);
    Picasso.get().load(imageBaseUrl + intentImage).into(image);
    loadTrailers();
    loadReviews();

    favouriteButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {

        Favourite favourite = new Favourite(movieId, intentTitle, intentVoteAverage, intentImage, intentSynopsis, intentReleaseDate);

        for (Favourite favouriteItem : favouriteList) {
          if (favouriteItem.getMovieId().equals(movieId)) {
            favourite.setId(favouriteItem.getId());
            break;
          }
        }

        boolean has = favouriteList.stream().anyMatch(e -> e.getMovieId().equals(movieId));

        if (has) {
          favouriteViewModel.delete(favourite);
        } else  {
          favouriteViewModel.insert(favourite);
        }

      }
    });
  }

  private void loadTrailers() {
    trailerRecyclerView = findViewById(R.id.trailerRecyclerView);
    trailerRecyclerView.setHasFixedSize(true);
    horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

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

        trailerRecyclerView.setLayoutManager(horizontalLayoutManager);
        trailerRecyclerView.setAdapter(adapter);
      }

      @Override public void onFailure(Call<MovieTrailer> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }

  private void loadReviews() {
    reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
    reviewRecyclerView.setHasFixedSize(true);
    verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    MoviesApi moviesApi = retrofit.create(MoviesApi.class);
    Call<MovieReview> call = moviesApi.getReviews(movieId, apiKey, language, page);

    ArrayList<MovieReview.ResultsBean> movieReviews = new ArrayList<>();

    call.enqueue(new Callback<MovieReview>() {
      @Override public void onResponse(Call<MovieReview> call, Response<MovieReview> response) {
        MovieReview movieReview = response.body();
        assert movieReview != null;
        List<MovieReview.ResultsBean> parsedMovieReviewsList = movieReview.getResults();

        movieReviews.addAll(parsedMovieReviewsList);

        adapter = new MovieReviewAdapter(movieReviews);

        reviewRecyclerView.setLayoutManager(verticalLayoutManager);
        reviewRecyclerView.setAdapter(adapter);
      }

      @Override public void onFailure(Call<MovieReview> call, Throwable t) {
        t.printStackTrace();
      }
    });
  }
}