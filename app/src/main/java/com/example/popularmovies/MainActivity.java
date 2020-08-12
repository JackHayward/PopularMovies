package com.example.popularmovies;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    new RetrieveMovies().execute("sdsdsd");
  }

  public static class RetrieveMovies extends AsyncTask<String, Void, String> {

    @Override protected String doInBackground(String... strings) {
      String string = null;

      URL url = NetworkUtils.buildUrl("popularity");
      try {
        string = NetworkUtils.getResponseFromHttpUrl(url);
      } catch (IOException e) {
        e.printStackTrace();
      }

      return string;
    }
  }
}