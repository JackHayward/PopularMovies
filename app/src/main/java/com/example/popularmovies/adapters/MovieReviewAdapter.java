package com.example.popularmovies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.R;
import com.example.popularmovies.models.MovieReview;
import com.example.popularmovies.models.MovieTrailer;
import java.util.ArrayList;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.MovieReviewViewHolder> {

  private ArrayList<MovieReview.ResultsBean> movieList;

  public static class MovieReviewViewHolder extends RecyclerView.ViewHolder {
    public TextView movieTrailerTitle;
    public CardView cardView;

    public MovieReviewViewHolder(@NonNull View itemView) {
      super(itemView);
      movieTrailerTitle = itemView.findViewById(R.id.movie_trailer_title);
      cardView = (CardView) itemView.findViewById(R.id.trailer_view);
    }
  }

  public MovieReviewAdapter(ArrayList<MovieReview.ResultsBean> movieList) {
    this.movieList = movieList;
  }

  @NonNull @Override
  public MovieReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_item, parent, false);
    return new MovieReviewViewHolder(v);
  }

  @Override public void onBindViewHolder(@NonNull MovieReviewViewHolder holder, int position) {
    MovieReview.ResultsBean currentItem = movieList.get(position);

    //holder.movieTrailerTitle.setText(currentItem.getName());
    //holder.cardView.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View view) {
    //
    //  }
    //});
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}