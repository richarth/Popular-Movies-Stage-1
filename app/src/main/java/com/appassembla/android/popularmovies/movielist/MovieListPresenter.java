package com.appassembla.android.popularmovies.movielist;

import android.support.annotation.NonNull;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;

import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class MovieListPresenter implements MovieListEvents {

    private final MovieListView movieListView;
    private final MoviesRepository moviesRepository;

    public MovieListPresenter(@NonNull MovieListView movieListView, @NonNull MoviesRepository moviesRepository) {
        this.movieListView = movieListView;
        this.moviesRepository = moviesRepository;
    }

    public void displayMovies(int movieListSortType) {
        List<Movie> movies = moviesRepository.getMovies(movieListSortType);

        movieListView.showProgressBar();

        if (movies.isEmpty()) {
            movieListView.displayNoMoviesMessage();
        } else {
            movieListView.displayMoviesList(movies);
        }

        movieListView.hideProgressBar();
    }

    @Override
    public void movieClicked(int movieId, int adapterPosition) {
         movieListView.displayMovieDetail(movieId, adapterPosition);
    }
}
