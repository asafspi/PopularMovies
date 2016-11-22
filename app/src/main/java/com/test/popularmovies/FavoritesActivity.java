package com.test.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.user.popularmovies.R;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favoritesRecyclerView;
    FavoritesAdapter favoritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        getFavoritesListFromShared();
        setVies();

    }

    private void getFavoritesListFromShared() {

    }

    private void setVies() {
        favoritesRecyclerView = (RecyclerView) findViewById(R.id.favoriteRecyclerView);
        favoritesRecyclerView.setHasFixedSize(true);
        favoritesRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        favoritesAdapter = new FavoritesAdapter(GetDataForMovies.favorites, getApplicationContext());
        favoritesRecyclerView.setAdapter(favoritesAdapter);
    }


}
