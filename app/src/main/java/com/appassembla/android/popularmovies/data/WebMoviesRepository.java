package com.appassembla.android.popularmovies.data;

import android.util.Log;

import com.appassembla.android.popularmovies.BuildConfig;
import com.appassembla.android.popularmovies.movielist.MovieListPresenter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by richard.thompson on 08/02/2017.
 */

public class WebMoviesRepository implements MoviesRepository {

    private final MovieDBService movieDBService;

    private static final String TAG = "WebMoviesRepository";

    private Movie selectedMovie = null;

    private MovieListPresenter presenter;

    public WebMoviesRepository(MovieListPresenter presenter) {
        this.presenter = presenter;
        Moshi moshi = new Moshi.Builder()
                .add(MovieAdapterFactory.create())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        movieDBService = retrofit.create(MovieDBService.class);
    }

    @Override
    public void fetchMovies(int sortType) {

        Observable<MoviesListing> moviesData;

        if (sortType == MoviesRepository.TOP_RATED_SORT_TYPE) {
            moviesData = movieDBService.getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY);
        } else {
            moviesData = movieDBService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
        }

        moviesData.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::moviesFetched,this::noMoviesFetched);
    }

    @Override
    public Movie getMovieById(int movieId) {

        /*Call<Movie> getMovieDetailsCall = movieDBService.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY);

        final CountDownLatch executionCompleted = new CountDownLatch(1);

        new Thread(() -> {
            try {
                selectedMovie = getMovieDetailsCall.execute().body();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }

            executionCompleted.countDown();

        }).start();

        // Wait for data
        try
        {
            executionCompleted.await();
        }
        catch (InterruptedException e)
        {
            Log.d(TAG, e.getMessage());
        }*/

        return selectedMovie;
    }

    private void moviesFetched(MoviesListing moviesListing) {
        presenter.moviesFetched(moviesListing.results());
    }

    private void noMoviesFetched(Throwable throwable) {
        Log.d(TAG, throwable.getMessage());

        presenter.moviesFetched(Collections.EMPTY_LIST);
    }
}
