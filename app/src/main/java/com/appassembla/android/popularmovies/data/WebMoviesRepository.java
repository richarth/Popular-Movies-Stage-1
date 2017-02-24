package com.appassembla.android.popularmovies.data;

import com.appassembla.android.popularmovies.BuildConfig;
import com.squareup.moshi.Moshi;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by richard.thompson on 08/02/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class WebMoviesRepository implements MoviesRepository {

    private final MovieDBService movieDBService;

    public WebMoviesRepository() {
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
    public Single<MoviesListing> getMovies(int sortType) {
        Single<MoviesListing> moviesData;

        if (sortType == MoviesRepository.TOP_RATED_SORT_TYPE) {
            moviesData = movieDBService.getTopRatedMovies(BuildConfig.MOVIE_DB_API_KEY);
        } else {
            moviesData = movieDBService.getPopularMovies(BuildConfig.MOVIE_DB_API_KEY);
        }

        return moviesData;
    }

    @Override
    public Single<Movie> getMovieById(int movieId) {
        return movieDBService.getMovieDetails(movieId, BuildConfig.MOVIE_DB_API_KEY);
    }
}
