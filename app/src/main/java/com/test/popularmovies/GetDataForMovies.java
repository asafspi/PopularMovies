package com.test.popularmovies;


import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetDataForMovies {

    public static ArrayList<Movie> popularMovie = new ArrayList<>();
    public static ArrayList<Movie> favorites = new ArrayList<>();
    private static String apiKey = ""; //TODO change to your api key
    private static String title, path, rating, releaseDate, overview, youTubeId;
    private static int index;
    static void getDataFromServer(String popularOrTopRated) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String stringUrl = "http://api.themoviedb.org/3/movie/" + popularOrTopRated + "?api_key=" + apiKey;
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
                index = i;
                title = jsonObjectOffer.getString("original_title");
                path = jsonObjectOffer.getString("poster_path");
                rating = jsonObjectOffer.getString("vote_average");
                releaseDate = jsonObjectOffer.getString("release_date");
                overview = jsonObjectOffer.getString("overview");
                String id = jsonObjectOffer.getString("id");
                getDataForMovie(id);
                getReviewsForMovies(id);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException | SecurityException | OutOfMemoryError e) {
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

    private static void getDataForMovie(String id) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String stringUrl = "http://api.themoviedb.org/3/movie/" + id + "/videos" + "?api_key=" + apiKey;
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
            JSONObject jsonObjectOffer = mainJsonArr.getJSONObject(0);
            youTubeId = jsonObjectOffer.getString("key");
            //popularMovie.add(index, new Movie(title, "http://image.tmdb.org/t/p/w154" + path, rating, releaseDate, overview, false, id, youTubeId));
            //Log.d("ZAQ", popularMovie.get(index).getYouTubeId());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException | SecurityException | OutOfMemoryError e) {
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

    private static void getReviewsForMovies(String id) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String stringUrl = "http://api.themoviedb.org/3/movie/" + id + "/reviews" + "?api_key=" + apiKey;
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
            JSONObject jsonObjectOffer;
            if(mainJsonArr.isNull(0)){
                return;
            }
            jsonObjectOffer = mainJsonArr.getJSONObject(0);
            String reviewUrl = jsonObjectOffer.getString("url");
            popularMovie.add(new Movie(title, "http://image.tmdb.org/t/p/w154" + path, rating, releaseDate, overview, false, id, youTubeId, reviewUrl));
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException | SecurityException | OutOfMemoryError e) {
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
