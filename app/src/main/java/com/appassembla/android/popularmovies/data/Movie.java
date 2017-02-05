package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

import java.util.Date;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

@AutoValue
public abstract class Movie {
    public abstract int movieId();
    public abstract String movieName();
    public abstract String posterUrl();
    public abstract String plotSynopsis();
    public abstract float averageRating();
    public abstract Date releaseDate();

    public static Movie create(int movieId, String movieName, String posterUrl, String plotSynopsis, float averageRating, Date releaseDate) {
        return new AutoValue_Movie(movieId, movieName, posterUrl, plotSynopsis, averageRating, releaseDate);
    }
}
