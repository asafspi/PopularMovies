package com.example.user.popularmovies.helpers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.popularmovies.R;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ShPref.checkIfFavoriteExist();
    }
}
