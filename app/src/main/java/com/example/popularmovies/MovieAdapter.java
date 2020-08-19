package com.example.popularmovies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.models.MovieResults;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

  private ArrayList<MovieResults.ResultsBean> movieList;
  private static String imageBaseUrl = "http://image.tmdb.org/t/p/w185/";

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView movieImage;
    public TextView movieTitle;

    public MovieViewHolder(@NonNull View itemView) {
      super(itemView);
      movieImage = itemView.findViewById(R.id.movie_image);
      movieTitle = itemView.findViewById(R.id.movie_title);
    }
  }

  public MovieAdapter(ArrayList<MovieResults.ResultsBean> movieList) {
    this.movieList = movieList;
  }

  @NonNull @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
    MovieViewHolder movieViewHolder = new MovieViewHolder(v);
    return movieViewHolder;
  }

  @Override public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    MovieResults.ResultsBean currentItem = movieList.get(position);

    holder.movieTitle.setText(currentItem.getTitle());
    Picasso.get().load(imageBaseUrl + currentItem.getPoster_path()).into(holder.movieImage);
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}
