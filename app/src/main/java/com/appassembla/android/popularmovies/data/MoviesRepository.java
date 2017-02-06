package com.appassembla.android.popularmovies.data;

import java.util.List;

/**
 * Created by Richard Thompson on 04/02/2017.
 */

public interface MoviesRepository {
    List<Movie> getMovies();

    Movie getMovieById(int movieId);
}
