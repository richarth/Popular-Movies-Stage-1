package com.appassembla.android.popularmovies.moviedetail;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;
import com.appassembla.android.popularmovies.data.StaticMoviesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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

    private static final List<Movie> SOME_MOVIES = new StaticMoviesRepository().getMovies();

    @Before
    public void setUp() throws Exception {
        movieId = 7;

        movieDetailsPresenter = new MovieDetailsPresenter(movieDetailsView, moviesRepository, movieId);
    }

    @Test
    public void shouldShowMovieDetail() {
        when(moviesRepository.getMovieById(movieId)).thenReturn(SOME_MOVIES.get(movieId));

        movieDetailsPresenter.displayMovie();

        verify(movieDetailsView).displayMovieDetails(SOME_MOVIES.get(movieId));
    }

    @Test
    public void shouldShowNoMovieDetails() {
        when(moviesRepository.getMovieById(1000000)).thenReturn(null);

        movieDetailsPresenter.displayMovie();

        verify(movieDetailsView).displayMovieDetails(null);
    }
}