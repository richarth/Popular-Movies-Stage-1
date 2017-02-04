package com.appassembla.android.popularmovies.movielist;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Created by richardthompson on 04/02/2017.
 */

public class StaticMovieListRepository implements MovieListRepository {
    @Override
    @NonNull
    public List<Movie> getMovies() {
        Movie movie1 = new Movie();
        movie1.setMovieId(1);
        movie1.setMovieName("Movie 1");

        Movie movie2 = new Movie();
        movie2.setMovieId(2);
        movie2.setMovieName("Movie 2");

        Movie movie3 = new Movie();
        movie3.setMovieId(3);
        movie3.setMovieName("Movie 3");

        return Arrays.asList(movie1, movie2, movie3);
    }
}
