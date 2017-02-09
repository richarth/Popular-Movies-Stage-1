package com.appassembla.android.popularmovies.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by richard.thompson on 08/02/2017.
 */

interface MovieDBService {
    @GET("3/movie/popular")
    Call<List<Movie>> getPopularMovies(@Query("api_key") String apiKey);

    @GET("3/movie/top_rated")
    Call<List<Movie>> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("3/movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int movieId, @Query("api_key") String apiKey);
}
