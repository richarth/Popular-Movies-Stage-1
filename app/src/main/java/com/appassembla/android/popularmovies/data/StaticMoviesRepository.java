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
        Movie movie7 = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, new Date());

        Movie movie4 = Movie.create(4, "Movie 4", "http://i.imgur.com/DvpvklR.png", "Movie 4 is about an archaeologist", 0, new Date());

        Movie movie8 = Movie.create(8, "Movie 8", "http://i.imgur.com/DvpvklR.png", "Movie 8 is about an alien", 0, new Date());

        movies = Arrays.asList(movie7, movie4, movie8);
    }

    @Override
    @NonNull
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(final int movieId) {

        Movie selectedMovie = null;

        if (movies.stream().filter(m -> m.id() == movieId).findFirst().isPresent()) {
            selectedMovie = movies.stream().filter(m -> m.id() == movieId).findFirst().get();
        }

        return selectedMovie;
    }
}
