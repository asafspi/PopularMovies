package com.example.user.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.popularmovies.helpers.ShPref;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private int position;
    private ImageView favorite;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        position = intent.getIntExtra("moviePosition", 0);
        setVies();
        if(ShPref.checkIfOnFavorites(header.getText().toString())){
            favorite.setImageResource(R.drawable.ic_star);
        }else {
            favorite.setImageResource(R.drawable.ic_star_empty);
        }
    }

    private void setVies() {
        header = (TextView) findViewById(R.id.header_text);
        TextView overview = (TextView) findViewById(R.id.textView_overview);
        TextView rating = (TextView) findViewById(R.id.textViewRating);
        TextView release = (TextView) findViewById(R.id.textViewRelease);
        favorite = (ImageView) findViewById(R.id.favoriteButton);
        favorite.setOnClickListener(this);
        //getActionBar().setTitle(GetDataForMovies.popularMovie.get(position).getTitle());
        ImageView poster = (ImageView) findViewById(R.id.imageViewMovieActivity);
        header.setText(GetDataForMovies.popularMovie.get(position).getTitle());
        release.setText(GetDataForMovies.popularMovie.get(position).getReleaseDate());
        overview.setText(GetDataForMovies.popularMovie.get(position).getOverview());
        rating.setText(GetDataForMovies.popularMovie.get(position).getRating());
        Picasso.with(getApplicationContext()).load(GetDataForMovies.popularMovie.get(position).getPath())
                .into(poster);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.favoriteButton:
                Log.d("zaq", "Favorite button");
                ShPref.saveToFavorites(header.getText().toString(), position);
                break;
        }
    }
}
