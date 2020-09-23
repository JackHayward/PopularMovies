package com.example.popularmovies.adapters;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.R;
import com.example.popularmovies.models.MovieTrailer;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerViewHolder> {

  private ArrayList<MovieTrailer.ResultsBean> movieList;

  public static class MovieTrailerViewHolder extends RecyclerView.ViewHolder {
    public TextView movieTrailerTitle;
    public CardView cardView;

    public MovieTrailerViewHolder(@NonNull View itemView) {
      super(itemView);
      movieTrailerTitle = itemView.findViewById(R.id.movie_trailer_title);
      cardView = (CardView) itemView.findViewById(R.id.trailer_view);
    }
  }

  public MovieTrailerAdapter(ArrayList<MovieTrailer.ResultsBean> movieList) {
    this.movieList = movieList;
  }

  @NonNull @Override
  public MovieTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_item, parent, false);
    MovieTrailerViewHolder movieTrailerViewHolder = new MovieTrailerViewHolder(v);
    return movieTrailerViewHolder;
  }

  @Override public void onBindViewHolder(@NonNull MovieTrailerViewHolder holder, int position) {
    MovieTrailer.ResultsBean currentItem = movieList.get(position);

    holder.movieTrailerTitle.setText(currentItem.getName());
    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + currentItem.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=" + currentItem.getKey()));
        try {
          view.getContext().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
          view.getContext().startActivity(webIntent);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}
