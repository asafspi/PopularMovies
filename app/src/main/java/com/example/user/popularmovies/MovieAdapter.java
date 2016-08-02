package com.example.user.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final ArrayList<Movie> moviesList;
    private Context mContext;
    SharedPreferences prefs;



    public class ViewHolder extends RecyclerView.ViewHolder {


        public ImageView movieImage;
        public TextView movieLabel;
        public RelativeLayout mainRelativeLayout;
        private ImageView favoritesButton;

        public ViewHolder(View v) {
            super(v);
            movieLabel = (TextView) v.findViewById(R.id.cell_label);
            movieImage = (ImageView) v.findViewById(R.id.cell_image);
            mainRelativeLayout = (RelativeLayout) v.findViewById(R.id.cell_main);
        }
    }

    public MovieAdapter(ArrayList<Movie> movies, Context context) {
        this.moviesList= movies;
        this.mContext = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
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
                holder.mainRelativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("zaq", moviesList.get(position).getTitle());
                        Intent intent = new Intent(holder.movieLabel.getContext(), MovieActivity.class);
                        intent.putExtra("moviePosition", position);
                        holder.movieLabel.getContext().startActivity(intent);
                    }
                });
    }
    @Override
    public int getItemCount() {
                return moviesList.size();
    }
}

