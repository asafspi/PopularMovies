package com.example.user.popularmovies;


public class Movie {

    private String title, path, rating, releaseDate, overview;
    private boolean isFavorite;

    public Movie(String title, String path, String rating, String releaseDate, String overview, boolean isFavorite) {
        this.title = title;
        this.path = path;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.isFavorite = isFavorite;

    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

}

