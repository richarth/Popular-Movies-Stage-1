package com.appassembla.android.popularmovies.data;

import com.squareup.moshi.Json;

import java.util.Date;

/**
 * Created by richard.thompson on 09/02/2017.
 */

public class Movie2 {
    private int id;
    @Json(name = "original_title")
    private String name;
    @Json(name = "poster_path")
    private String posterUrl;
    @Json(name = "overview")
    private String plotSynopsis;
    @Json(name = "vote_average")
    private double averageRating;
    @Json(name = "release_date")
    private String releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public void setPlotSynopsis(String plotSynopsis) {
        this.plotSynopsis = plotSynopsis;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
