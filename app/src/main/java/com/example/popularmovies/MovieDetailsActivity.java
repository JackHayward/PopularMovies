package com.example.popularmovies;

import android.content.Intent;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

  private TextView title,releaseDate,voteAverage,synopsis;
  private ImageView image;
  private static String imageBaseUrl = "http://image.tmdb.org/t/p/w185/";

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

    title.setText(intentTitle);
    synopsis.setText(intentSynopsis);
    releaseDate.setText(intentReleaseDate);
    voteAverage.setText(intentVoteAverage);
    Picasso.get().load(imageBaseUrl + intentImage).into(image);
  }
}