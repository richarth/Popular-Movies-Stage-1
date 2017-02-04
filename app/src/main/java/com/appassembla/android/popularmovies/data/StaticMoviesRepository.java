package com.appassembla.android.popularmovies.data;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class StaticMoviesRepository implements MoviesRepository {

    private List<Movie> movies;

    public StaticMoviesRepository() {
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Movie 1");

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("Movie 2");

        Movie movie3 = new Movie();
        movie3.setMovieId(3);
        movie3.setMovieName("Movie 3");

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
