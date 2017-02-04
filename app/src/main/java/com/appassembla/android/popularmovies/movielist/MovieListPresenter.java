package com.appassembla.android.popularmovies.movielist;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by richardthompson on 04/02/2017.
 */

public class MovieListPresenter implements MovieListEvents {

    private final MovieListView movieListView;
    private final MovieListRepository movieListRepository;
    private List<Movie> movies;

    public MovieListPresenter(@NonNull MovieListView movieListView, @NonNull MovieListRepository movieListRepository) {
        this.movieListView = movieListView;
        this.movieListRepository = movieListRepository;
    }

    public void displayMovies() {
        movies = movieListRepository.getMovies();

        if (movies.isEmpty()) {
            movieListView.displayNoMoviesMessage();
        } else {
            movieListView.displayMoviesList(movies);
        }
    }

    @Override
    public void movieClicked(int position) {
        Movie selectedMovie = movies.get(position);

        movieListView.displayMovieDetail(selectedMovie);
    }
}
