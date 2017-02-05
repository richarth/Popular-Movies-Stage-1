package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class StaticMoviesRepository implements MoviesRepository {

    private List<Movie> movies;

    public StaticMoviesRepository() {
        Movie movie1 = Movie.create(1, "Movie 1", "", "", 0, new Date());

        Movie movie2 = Movie.create(2, "Movie 2", "", "", 0, new Date());

        Movie movie3 = Movie.create(3, "Movie 3", "", "", 0, new Date());

        movies = Arrays.asList(movie1, movie2, movie3);
    }

    @Override
    @NonNull
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    @NonNull
    public Movie getMovie(int movieId) {
        return movies.get(movieId);
    }
}
