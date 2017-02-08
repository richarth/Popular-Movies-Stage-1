package com.appassembla.android.popularmovies.data;

import android.util.Log;

import com.appassembla.android.popularmovies.BuildConfig;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by richard.thompson on 08/02/2017.
 */

public class WebMoviesRepository implements MoviesRepository {

    private MovieDBService movieDBService;

    private static final String TAG = "WebMoviesRepository";

    public WebMoviesRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        movieDBService = retrofit.create(MovieDBService.class);
    }

    @Override
    public List<Movie> getMovies(int sortType) {

        List<Movie> moviesFound = Collections.EMPTY_LIST;

        Call<List<Movie>> getMoviesCall = null;

        try {
            if (sortType == MoviesRepository.POPULAR_SORT_TYPE) {
                getMoviesCall = movieDBService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
            } else if (sortType == MoviesRepository.TOP_RATED_SORT_TYPE) {
                getMoviesCall = movieDBService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
            }

            if (getMoviesCall != null) {
                moviesFound = getMoviesCall.execute().body();
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return moviesFound;
    }

    @Override
    public Movie getMovieById(int movieId) {

        Movie selectedMovie = Movie.create(0, "", "", "", 0, new Date());

        try {
            Call<Movie> getMovieDetailsCall = movieDBService.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY);

            selectedMovie = getMovieDetailsCall.execute().body();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return selectedMovie;
    }
}
