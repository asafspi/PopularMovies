package com.example.user.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<Movie> moviesList;
    private Context mContext;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView gameLabel;
        public ImageView gameImage;
        public RelativeLayout playButton;
        public ImageView movieImage;
        public TextView movieLabel;

        public ViewHolder(View v) {
            super(v);
            movieLabel = (TextView) v.findViewById(R.id.cell_label);
            movieImage = (ImageView) v.findViewById(R.id.cell_image);
        }
    }

    public MovieAdapter(ArrayList<Movie> movies, Context context) {
        this.moviesList= movies;
        this.mContext = context;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_cell, parent, false);
        return new ViewHolder(v1);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int p) {
        final int position = holder.getAdapterPosition();
                holder.movieLabel.setText(moviesList.get(position).getTitle());
                Picasso.with(holder.movieImage.getContext()).load(moviesList.get(position).getPath())
                        .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
                return moviesList.size();
    }
}

