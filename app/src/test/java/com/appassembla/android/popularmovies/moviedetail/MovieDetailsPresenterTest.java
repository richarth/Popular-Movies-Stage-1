package com.appassembla.android.popularmovies.moviedetail;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesListing;
import com.appassembla.android.popularmovies.data.MoviesRepository;
import com.appassembla.android.popularmovies.data.StaticMoviesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import static io.reactivex.Single.just;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Richard Thompson on 04/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {
    @Mock
    private MovieDetailsView movieDetailsView;
    @Mock
    private MoviesRepository moviesRepository;
    private MovieDetailsPresenter movieDetailsPresenter;
    private int movieId;

    private static final int SELECTED_MOVIE_POSITION = 0;
    private static final int MOVIE_SORT_TYPE = 0;

    private static final Observable<MoviesListing> SOME_MOVIES = new StaticMoviesRepository().getMovies(MOVIE_SORT_TYPE);

    private static final Single<Movie> SELECTED_MOVIE_OBSERVABLE = just(SOME_MOVIES.blockingSingle().results().get(SELECTED_MOVIE_POSITION));

    @Before
    public void setUp() {
        movieId = 7;

        movieDetailsPresenter = new MovieDetailsPresenter(movieDetailsView, moviesRepository, movieId);
    }

    @Test
    public void shouldShowMovieDetail() {
        when(moviesRepository.getMovieById(movieId)).thenReturn(SELECTED_MOVIE_OBSERVABLE);

        movieDetailsPresenter.displayMovie();

        verify(movieDetailsView).displayMovieDetails(SELECTED_MOVIE_OBSERVABLE.blockingGet());
    }

    @Test
    public void shouldShowNoMovieDetails() {
        movieDetailsPresenter.displayMovie();

        verify(movieDetailsView).displayMovieDetails(null);
    }
}