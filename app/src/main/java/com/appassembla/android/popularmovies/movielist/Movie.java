package com.appassembla.android.popularmovies.movielist;

import android.support.annotation.NonNull;

/**
 * Created by richardthompson on 04/02/2017.
 */

public class Movie {
    private int movieId;

    private String movieName;

    @NonNull
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(@NonNull String movieName) {
        this.movieName = movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(@NonNull int movieId) {
        this.movieId = movieId;
    }
}
