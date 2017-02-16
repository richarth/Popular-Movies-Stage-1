package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

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
    @Json(name = "backdrop_path")
    public abstract String backdropUrl();

    public static final String IMG_BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String POSTER_IMG_SIZE = "w500";
    public static final String HERO_IMG_SIZE = "w780";

    public static Movie create(int id, @NonNull String name, @NonNull String posterUrl, @NonNull String plotSynopsis, double averageRating, @NonNull String releaseDate, @NonNull String backdropUrl) {
        return new AutoValue_Movie(id, name, posterUrl, plotSynopsis, averageRating, releaseDate, backdropUrl);
    }

    public static JsonAdapter<Movie> jsonAdapter(Moshi moshi) {
        return new AutoValue_Movie.MoshiJsonAdapter(moshi);
    }
}
