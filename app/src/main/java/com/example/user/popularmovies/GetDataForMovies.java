package com.example.user.popularmovies;


import android.content.Context;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetDataForMovies {

    public static ArrayList<Movie> popularMovie = new ArrayList<>();

    public static void getDataFromServer(String popularOrTopRated) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String stringUrl = "http://api.themoviedb.org/3/movie/" + popularOrTopRated + "?api_key=1a8de05b42e33ad9bf8733b11cd5529d";
        //String stringUrl = "http://m.softgames.de/categories/latest-games.json/?p=belmedia_belauncher";
        StringBuilder url = new StringBuilder(stringUrl);
        HttpURLConnection connection = null;
        InputStream in = null;
        JSONObject jsonObject;
        try {
            connection = (HttpURLConnection) new URL(url.toString()).openConnection();
            in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String read = br.readLine();
            jsonObject = new JSONObject(read);
            JSONArray mainJsonArr = jsonObject.getJSONArray("results");
            for (int i = 0; i < mainJsonArr.length(); i++) {
                JSONObject jsonObjectOffer = mainJsonArr.getJSONObject(i);

                String title = jsonObjectOffer.getString("original_title");
                String path = jsonObjectOffer.getString("poster_path");
                String rating = jsonObjectOffer.getString("vote_average");
                String releaseDate = jsonObjectOffer.getString("release_date");
                String overview = jsonObjectOffer.getString("overview");
                popularMovie.add(i, new Movie(title, "http://image.tmdb.org/t/p/w154" + path, rating, releaseDate, overview));
                Log.d("ZAQ", popularMovie.get(i).getTitle());
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException | SecurityException e) {
            //ExceptionHandler.handleException(e);
            // Asaf sometimes SecurityException occurs, check it on Fabric
        } catch (OutOfMemoryError e) {
            //ExceptionHandler.handleException(e);
            // Asaf sometimes SecurityException occurs, check it on Fabric
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
