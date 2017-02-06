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
    private int clickedPosition;

    private static final List<Movie> SOME_MOVIES = new StaticMoviesRepository().getMovies();

    @Before
    public void setUp() throws Exception {
        clickedPosition = 2;

        movieDetailsPresenter = new MovieDetailsPresenter(movieDetailsView, moviesRepository, clickedPosition);
    }

    @Test
    public void shouldShowMovieDetail() {
        when(moviesRepository.getMovieById(clickedPosition)).thenReturn(SOME_MOVIES.get(clickedPosition));

        movieDetailsPresenter.displayMovie();

        verify(movieDetailsView).displayMovieDetails(SOME_MOVIES.get(clickedPosition));
    }
}