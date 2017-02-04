package com.appassembla.android.popularmovies.moviedetail;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class MovieDetailsPresenter {
    private final MovieDetailsView movieDetailsView;
    private final MoviesRepository moviesRepository;

    public MovieDetailsPresenter(MovieDetailsView movieDetailsView, MoviesRepository moviesRepository) {
        this.movieDetailsView = movieDetailsView;
        this.moviesRepository = moviesRepository;
    }

    public void displayMovie(int movieId) {
        Movie selectedMovie = moviesRepository.getMovie(movieId);

        movieDetailsView.displayMovieDetails(selectedMovie);
    }
}
