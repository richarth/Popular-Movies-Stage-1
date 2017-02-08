package com.appassembla.android.popularmovies.data;

import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public interface MoviesRepository {
    public static final int POPULAR_SORT_TYPE = 1;
    public static final int TOP_RATED_SORT_TYPE = 2;

    List<Movie> getMovies(int sortType);

    Movie getMovieById(int movieId);
}
