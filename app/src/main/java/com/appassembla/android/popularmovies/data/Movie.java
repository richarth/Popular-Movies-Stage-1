package com.appassembla.android.popularmovies.data;

import com.google.auto.value.AutoValue;

import java.util.Date;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

@AutoValue
public abstract class Movie {
    public abstract int id();
    public abstract String name();
    public abstract String posterUrl();
    public abstract String plotSynopsis();
    public abstract float averageRating();
    public abstract Date releaseDate();

    public static Movie create(int id, String name, String posterUrl, String plotSynopsis, float averageRating, Date releaseDate) {
        return new AutoValue_Movie(id, name, posterUrl, plotSynopsis, averageRating, releaseDate);
    }
}
