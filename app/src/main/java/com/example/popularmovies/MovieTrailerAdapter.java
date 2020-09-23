package com.example.popularmovies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.models.MovieTrailer;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

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
    //holder.cardView.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View view) {
    //
    //    //Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
    //    //intent.putExtra("Title", movieList.get(position).getTitle());
    //    //intent.putExtra("ReleaseDate", movieList.get(position).getRelease_date());
    //    //intent.putExtra("VoteAverage", Double.toString(movieList.get(position).getVote_average()));
    //    //intent.putExtra("Synopsis", movieList.get(position).getOverview());
    //    //intent.putExtra("Image", movieList.get(position).getPoster_path());
    //    //intent.putExtra("Id", movieList.get(position).getId());
    //    //view.getContext().startActivity(intent);
    //
    //  }
    //});
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}
