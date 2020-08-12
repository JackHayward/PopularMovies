package com.example.popularmovies;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.popularmovies.models.Movie;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  private TextView textViewResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    textViewResult = findViewById(R.id.text_view_movie);
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://api.themoviedb.org/3/discover/movie/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    MoviesApi moviesApi = retrofit.create(MoviesApi.class);

    Call<List<Movie>> call = moviesApi.getMovies();

    call.enqueue(new Callback<List<Movie>>() {
      @Override public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {

        if(!response.isSuccessful()) {
          textViewResult.setText("response" + response.code());
          return;
        }

        List<Movie> movies = response.body();

        for (Movie movie : movies) {
          String content = "";

          content += "Title:" + movie.getTitle() + "\n";
          content += "Description:" + movie.getVoteCount() + "\n";

          textViewResult.append(content);
        }
      }

      @Override public void onFailure(Call<List<Movie>> call, Throwable t) {
        textViewResult.setText(t.getMessage());
      }
    });


    //textViewResult = findViewById(R.id.text_view_result);
    //Button buttonParse = findViewById(R.id.button_parse);
    //queue = Volley.newRequestQueue(this);

    //buttonParse.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View view) {
    //    jsonParse();
    //  }
    //});
  }

  //private void jsonParse() {
  //  URL url = NetworkUtils.buildUrl("popularity");
  //  JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url.toString(), null,
  //      new Response.Listener<JSONObject>() {
  //        @Override public void onResponse(JSONObject response) {
  //          try {
  //            JSONArray jsonArray = response.getJSONArray("results");
  //            for (int i = 0; i < jsonArray.length(); i++) {
  //              JSONObject movie = jsonArray.getJSONObject(i);
  //            }
  //          } catch (JSONException e) {
  //            e.printStackTrace();
  //          }
  //        }
  //      }, new Response.ErrorListener() {
  //    @Override public void onErrorResponse(VolleyError error) {
  //      error.printStackTrace();
  //    }
  //  });
  //}
}