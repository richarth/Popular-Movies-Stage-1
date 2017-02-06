package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class StaticMoviesRepository implements MoviesRepository {

    private final List<Movie> movies;

    public StaticMoviesRepository() {
        Movie movie7 = Movie.create(7, "Movie 7", "", "", 0, new Date());

        Movie movie4 = Movie.create(4, "Movie 4", "", "", 0, new Date());

        Movie movie8 = Movie.create(8, "Movie 8", "", "", 0, new Date());

        movies = Arrays.asList(movie7, movie4, movie8);
    }

    @Override
    @NonNull
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public Movie getMovie(final int movieId) {

        Movie selectedMovie = null;

        if (movies.stream().filter(m -> m.id() == movieId).findFirst().isPresent()) {
            selectedMovie = movies.stream().filter(m -> m.id() == movieId).findFirst().get();
        }

        return selectedMovie;
    }
}
