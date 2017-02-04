package com.appassembla.android.popularmovies.movielist;

/**
 * Created by richardthompson on 04/02/2017.
 */

public class Movie {
    private int movieId;

    private String movieName;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
