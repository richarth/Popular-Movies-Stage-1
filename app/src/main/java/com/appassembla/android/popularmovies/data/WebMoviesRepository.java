package com.appassembla.android.popularmovies.data;

import android.util.Log;

import com.appassembla.android.popularmovies.BuildConfig;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by richard.thompson on 08/02/2017.
 */

public class WebMoviesRepository implements MoviesRepository {

    private final MovieDBService movieDBService;

    private static final String TAG = "WebMoviesRepository";

    private Call<MoviesListing> getMoviesCall;

    private List<Movie> moviesFound = Collections.EMPTY_LIST;

    private Movie selectedMovie = null;

    public WebMoviesRepository() {
        Moshi moshi = new Moshi.Builder()
                .add(MovieAdapterFactory.create())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        movieDBService = retrofit.create(MovieDBService.class);
    }

    @Override
    public List<Movie> getMovies(int sortType) {
        if (sortType == MoviesRepository.POPULAR_SORT_TYPE) {
            getMoviesCall = movieDBService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
        } else if (sortType == MoviesRepository.TOP_RATED_SORT_TYPE) {
            getMoviesCall = movieDBService.getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY);
        }

        if (getMoviesCall != null) {
            final CountDownLatch executionCompleted = new CountDownLatch(1);

            new Thread(() -> {
                try {
                    MoviesListing moviesListing = getMoviesCall.execute().body();

                    moviesFound = moviesListing.results();

                    executionCompleted.countDown();
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());
                }

            }).start();

            // Wait for data
            try
            {
                executionCompleted.await();
            }
            catch (InterruptedException e)
            {
                Log.d(TAG, e.getMessage());
            }
        }

        return moviesFound;
    }

    @Override
    public Movie getMovieById(int movieId) {

        Call<Movie> getMovieDetailsCall = movieDBService.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY);

        final CountDownLatch executionCompleted = new CountDownLatch(1);

        new Thread(() -> {
            try {
                selectedMovie = getMovieDetailsCall.execute().body();

                executionCompleted.countDown();
            } catch (IOException e) {
                Log.d(TAG, e.getMessage());
            }

        }).start();

        // Wait for data
        try
        {
            executionCompleted.await();
        }
        catch (InterruptedException e)
        {
            Log.d(TAG, e.getMessage());
        }

        return selectedMovie;
    }
}
