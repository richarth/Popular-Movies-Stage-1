package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class Movie {
    private int movieId;

    private String movieName;

    private String posterUrl;

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

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    
    @NonNull
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(@NonNull String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
