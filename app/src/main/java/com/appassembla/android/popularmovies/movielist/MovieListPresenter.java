package com.appassembla.android.popularmovies.movielist;

import android.support.annotation.NonNull;
import android.util.Log;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesListing;
import com.appassembla.android.popularmovies.data.MoviesRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

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
        movieListView.showProgressBar();

        Observable<MoviesListing> movies = moviesRepository.getMovies(movieListSortType);

        movies.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::moviesFetched, this::movieFetchFailure);
    }

    private void moviesFetched(MoviesListing moviesListing) {
        List<Movie> moviesFound = moviesListing.results();

        if (moviesFound.isEmpty()) {
            movieListView.displayNoMoviesMessage();
        } else {
            movieListView.displayMoviesList(moviesFound);
        }
    }

    private void movieFetchFailure(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());

        movieListView.displayNoMoviesMessage();

        movieListView.hideProgressBar();
    }

    @Override
    public void movieClicked(int movieId, int adapterPosition) {
         movieListView.displayMovieDetail(movieId, adapterPosition);
    }
}
