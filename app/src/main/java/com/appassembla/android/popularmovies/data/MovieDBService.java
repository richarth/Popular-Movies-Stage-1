package com.appassembla.android.popularmovies.data;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by richard.thompson on 08/02/2017.
 */

interface MovieDBService {
    @GET("3/movie/popular")
    Observable<MoviesListing> getPopularMovies(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Observable<MoviesListing> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("3/movie/{id}")
    Single<Movie> getMovieDetails(@Path("id") int movieId, @Query("api_key") String apiKey);
}
