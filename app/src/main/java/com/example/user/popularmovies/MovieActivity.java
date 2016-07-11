package com.example.user.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {
    //        original title
//        movie poster image thumbnail
//        A plot synopsis (called overview in the api)
//        user rating (called vote_average in the api)
//        release date
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        position = intent.getIntExtra("moviePosition", 0);
        setVies();


    }

    private void setVies() {
        TextView header = (TextView)findViewById(R.id.header_text);
        TextView overview = (TextView)findViewById(R.id.textView_overview);
        TextView rating = (TextView)findViewById(R.id.textViewRating);
        TextView release = (TextView)findViewById(R.id.textViewRelease);
        //getActionBar().setTitle(GetDataForMovies.popularMovie.get(position).getTitle());
        ImageView poster = (ImageView) findViewById(R.id.imageViewMovieActivity);
        header.setText(GetDataForMovies.popularMovie.get(position).getTitle());
        release.setText(GetDataForMovies.popularMovie.get(position).getReleaseDate());
        overview.setText(GetDataForMovies.popularMovie.get(position).getOverview());
        rating.setText(GetDataForMovies.popularMovie.get(position).getRating());
        Picasso.with(getApplicationContext()).load(GetDataForMovies.popularMovie.get(position).getPath())
                .into(poster);
    }
}
