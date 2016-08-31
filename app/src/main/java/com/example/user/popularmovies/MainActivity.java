package com.example.user.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String POPULAR = "popular";
    private static String TOP_RATED = "top_rated";
    private MovieAdapter moviesAdapter;
    private RecyclerView mainRecyclerView;
    Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOnline();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GetDataForMovies.getDataFromServer(POPULAR);
        setView();
        checkIfFavoritesExist();
    }

    private void checkIfFavoritesExist() {
        try {
            /// Load Data if Favorites were exist
            Context context = this;
            Gson gson = new Gson();
            String array = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.JSonFavoritesMovies), null);
            Type type = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            if (array != null) {
                GetDataForMovies.favorites = gson.fromJson(array, type);
                Log.d("zaq1 ==== ", GetDataForMovies.favorites.toString());
            }
        } catch (NullPointerException e) {
            Log.d("zaq2 ==== " ,e + " ");
        }
    }

    public void setView() {
        mainRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        moviesAdapter = new MovieAdapter(GetDataForMovies.popularMovie, getApplicationContext());
        mainRecyclerView.setAdapter(moviesAdapter);
        favoritesButton = (Button) findViewById(R.id.buttonFavorites);
        favoritesButton.setOnClickListener(this);
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
            mainRecyclerView.setAdapter(moviesAdapter);
            return true;
        }else {
            GetDataForMovies.popularMovie.clear();
            GetDataForMovies.getDataFromServer(TOP_RATED);
            mainRecyclerView.setAdapter(moviesAdapter);
        }

        return super.onOptionsItemSelected(item);
    }
    public void isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(!(netInfo != null && netInfo.isConnectedOrConnecting())){
            Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonFavorites:
                startActivity(new Intent(this, FavoritesActivity.class));
        }
    }
}
