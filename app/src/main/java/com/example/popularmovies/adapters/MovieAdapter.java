package com.example.popularmovies.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmovies.MovieDetailsActivity;
import com.example.popularmovies.R;
import com.example.popularmovies.models.Favourite;
import com.example.popularmovies.models.Movie;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

  private ArrayList<Movie.ResultsBean> movieList;
  private List<Favourite> favourites = new ArrayList<>();
  private static String imageBaseUrl = "http://image.tmdb.org/t/p/w185/";

  public static class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView movieImage;
    public TextView movieTitle;
    public CardView cardView;

    public MovieViewHolder(@NonNull View itemView) {
      super(itemView);
      movieImage = itemView.findViewById(R.id.movie_image);
      movieTitle = itemView.findViewById(R.id.movie_title);
      cardView = (CardView) itemView.findViewById(R.id.card_view);
    }
  }

  public MovieAdapter(ArrayList<Movie.ResultsBean> movieList) {
    this.movieList = movieList;
  }

  @NonNull @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
    MovieViewHolder movieViewHolder = new MovieViewHolder(v);
    return movieViewHolder;
  }

  @Override public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    if (favourites.size() > 0) {
      Favourite currentItem = favourites.get(position);

      holder.movieTitle.setText(currentItem.getMovieTitle());
      Picasso.get().load(imageBaseUrl + currentItem.getPosterPath()).into(holder.movieImage);
      holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {

          Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
          intent.putExtra("Title", currentItem.getMovieTitle());
          intent.putExtra("ReleaseDate", currentItem.getReleaseDate());
          intent.putExtra("VoteAverage", currentItem.getUserRating());
          intent.putExtra("Synopsis", currentItem.getSynopsis());
          intent.putExtra("Image", currentItem.getPosterPath());
          intent.putExtra("Id", Integer.toString(currentItem.getId()));
          view.getContext().startActivity(intent);
        }
      });
    }
    else {
      Movie.ResultsBean currentItem = movieList.get(position);

      holder.movieTitle.setText(currentItem.getTitle());
      Picasso.get().load(imageBaseUrl + currentItem.getPoster_path()).into(holder.movieImage);
      holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {

          Intent intent = new Intent(view.getContext(), MovieDetailsActivity.class);
          intent.putExtra("Title", movieList.get(position).getTitle());
          intent.putExtra("ReleaseDate", movieList.get(position).getRelease_date());
          intent.putExtra("VoteAverage", Double.toString(movieList.get(position).getVote_average()));
          intent.putExtra("Synopsis", movieList.get(position).getOverview());
          intent.putExtra("Image", movieList.get(position).getPoster_path());
          intent.putExtra("Id", Integer.toString(movieList.get(position).getId()));
          view.getContext().startActivity(intent);

        }
      });
    }
  }

  public void setFavourites(List<Favourite> favourites) {
    this.favourites = favourites;
    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}
