package com.example.user.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String POPULAR = "popular";
    private static String TOP_RATED = "top_rated";
    private MovieAdapter gamesAdapter;
    private RecyclerView mainRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GetDataForMovies.getDataFromServer(POPULAR);
        setView();
    }

    public void setView() {

        mainRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        gamesAdapter = new MovieAdapter(GetDataForMovies.popularMovie, getApplicationContext());
        mainRecyclerView.setAdapter(gamesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent(this, MainActivity.class);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_most_popular) {
            GetDataForMovies.popularMovie.clear();
            GetDataForMovies.getDataFromServer(POPULAR);
            mainRecyclerView.setAdapter(gamesAdapter);
            return true;
        }else {
            GetDataForMovies.popularMovie.clear();
            GetDataForMovies.getDataFromServer(TOP_RATED);
            mainRecyclerView.setAdapter(gamesAdapter);
        }

        return super.onOptionsItemSelected(item);
    }

}
