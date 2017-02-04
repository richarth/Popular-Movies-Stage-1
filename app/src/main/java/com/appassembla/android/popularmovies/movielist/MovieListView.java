package com.appassembla.android.popularmovies.movielist;

import java.util.List;

/**
 * Created by richardthompson on 04/02/2017.
 */

public interface MovieListView {
    public void displayMoviesList(List<Movie> moviesList);
    public void displayNoMoviesMessage();
}
