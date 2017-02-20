package com.appassembla.android.popularmovies.movielist;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesListing;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

interface MovieListView {
    void displayMoviesList(List<Movie> moviesList);
    void displayNoMoviesMessage();
    void displayMovieDetail(int moviePosition, int adapterPosition);
    void showProgressBar();
    void hideProgressBar();
}
