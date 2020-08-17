package com.example.popularmovies;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  private TextView textViewResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textViewResult = findViewById(R.id.text_view_movie);

    MovieUtils movieUtils = new MovieUtils();
    movieUtils.applyMovieListToView(textViewResult);
  }
}