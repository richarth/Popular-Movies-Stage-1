package com.appassembla.android.popularmovies.movielist;

import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

interface MovieListView {
    void displayMoviesList(List<Movie> moviesList);
    void displayNoMoviesMessage();
    void displayMovieDetail(int moviePosition);
}
