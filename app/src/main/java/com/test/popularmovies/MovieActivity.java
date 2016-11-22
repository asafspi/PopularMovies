package com.test.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.user.popularmovies.R;
import com.test.popularmovies.helpers.ShPref;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private int position;
    private boolean isCameFromFavorites;
    private ImageView favorite;
    private TextView header;
    private String youTubeId, reviewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        position = intent.getIntExtra("moviePosition", 0);
        isCameFromFavorites = intent.getBooleanExtra("isFavorite", false);
        setVies();
        if (ShPref.checkIfOnFavorites(header.getText().toString())) {
            favorite.setImageResource(R.drawable.ic_star);
        } else {
            favorite.setImageResource(R.drawable.ic_star_empty);
        }
        youTubeId = GetDataForMovies.popularMovie.get(position).getYouTubeId();
        reviewUrl = GetDataForMovies.popularMovie.get(position).getReviewUrl();
    }

    private void setVies() {
        header = (TextView) findViewById(R.id.header_text);
        TextView overview = (TextView) findViewById(R.id.textView_overview);
        TextView rating = (TextView) findViewById(R.id.textViewRating);
        TextView release = (TextView) findViewById(R.id.textViewRelease);
        TextView trailerTextView = (TextView) findViewById(R.id.trailerTextView);
        trailerTextView.setOnClickListener(this);
        TextView reviewTextView = (TextView) findViewById(R.id.reviewTextView);
        reviewTextView.setOnClickListener(this);
        favorite = (ImageView) findViewById(R.id.favoriteButton);
        favorite.setOnClickListener(this);
        ImageView poster = (ImageView) findViewById(R.id.imageViewMovieActivity);

        if (isCameFromFavorites) {
            header.setText(GetDataForMovies.favorites.get(position).getTitle());
            release.setText(GetDataForMovies.favorites.get(position).getReleaseDate());
            overview.setText(GetDataForMovies.favorites.get(position).getOverview());
            rating.setText(GetDataForMovies.favorites.get(position).getRating());
            Picasso.with(getApplicationContext()).load(GetDataForMovies.favorites.get(position).getPath())
                    .into(poster);
        } else {
            header.setText(GetDataForMovies.popularMovie.get(position).getTitle());
            release.setText(GetDataForMovies.popularMovie.get(position).getReleaseDate());
            overview.setText(GetDataForMovies.popularMovie.get(position).getOverview());
            rating.setText(GetDataForMovies.popularMovie.get(position).getRating());
            Picasso.with(getApplicationContext()).load(GetDataForMovies.popularMovie.get(position).getPath())
                    .into(poster);
        }

    }

    @Override
    public void onClick(View view) {
        Gson gson = new GsonBuilder().create();
        switch (view.getId()) {
            case R.id.favoriteButton:
                if (ShPref.checkIfOnFavorites(header.getText().toString())) {
                    favorite.setImageResource(R.drawable.ic_star_empty);
                    ShPref.removeFromFavorites(header.getText().toString(), position);
                    ShPref.saveToFavorites(header.getText().toString(), position);
                } else {
                    ShPref.saveToFavorites(header.getText().toString(), position);
                    favorite.setImageResource(R.drawable.ic_star);
                }
                break;
            case R.id.trailerTextView:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youTubeId)));
                break;
            case R.id.reviewTextView:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(reviewUrl)));

                break;
        }
    }
}
