package com.appassembla.android.popularmovies.data;

import android.util.Log;

import java.util.Collections;
import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public interface MoviesRepository {
    int POPULAR_SORT_TYPE = 1;
    int TOP_RATED_SORT_TYPE = 2;

    void fetchMovies(int sortType);

    List<Movie> getMoviesFetched();

    Movie getMovieById(int movieId);
}
