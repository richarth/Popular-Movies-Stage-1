package com.appassembla.android.popularmovies.movielist;

import java.util.List;

/**
 * Created by richardthompson on 04/02/2017.
 */

public class MovieListPresenter {

    private final MovieListView movieListView;
    private final MovieListRepository movieListRepository;

    public MovieListPresenter(MovieListView movieListView, MovieListRepository movieListRepository) {
        this.movieListView = movieListView;
        this.movieListRepository = movieListRepository;
    }

    public void displayMovies() {
        List<Movie> movies = movieListRepository.getMovies();

        if (movies.isEmpty()) {
            movieListView.displayNoMoviesMessage();
        } else {
            movieListView.displayMoviesList(movies);
        }
    }
}
