package com.appassembla.android.popularmovies.moviedetail;

import android.support.annotation.NonNull;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public class MovieDetailsPresenter {
    private final MovieDetailsView movieDetailsView;
    private final MoviesRepository moviesRepository;
    private final int selectedMovieId;

    public MovieDetailsPresenter(@NonNull MovieDetailsView movieDetailsView, @NonNull MoviesRepository moviesRepository, int selectedMovieId) {
        this.movieDetailsView = movieDetailsView;
        this.moviesRepository = moviesRepository;
        this.selectedMovieId = selectedMovieId;
    }

    public void displayMovie() {
        Movie selectedMovie = moviesRepository.getMovie(selectedMovieId);

        movieDetailsView.displayMovieDetails(selectedMovie);
    }
}
