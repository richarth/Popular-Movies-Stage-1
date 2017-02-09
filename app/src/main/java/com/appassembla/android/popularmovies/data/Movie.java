package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.Date;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

@AutoValue
public abstract class Movie {
    public abstract int id();
    @Json(name = "original_title")
    public abstract String name();
    @Json(name = "poster_path")
    public abstract String posterUrl();
    @Json(name = "overview")
    public abstract String plotSynopsis();
    @Json(name = "vote_average")
    public abstract double averageRating();
    @Json(name = "release_date")
    public abstract String releaseDate();

    public static Movie create(int id, @NonNull String name, @NonNull String posterUrl, @NonNull String plotSynopsis, double averageRating, @NonNull String releaseDate) {
        return new AutoValue_Movie(id, name, posterUrl, plotSynopsis, averageRating, releaseDate);
    }

    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
