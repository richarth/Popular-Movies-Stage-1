package com.appassembla.android.popularmovies.movielist;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MovieListRepository;
import com.appassembla.android.popularmovies.data.StaticMovieListRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Richard Thompson on 04/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieListPresenterTest {

    @Mock
    private MovieListView movieListView;
    @Mock
    private MovieListRepository movieListRepository;
    private MovieListPresenter movieListPresenter;

    private static final List<Movie> SOME_MOVIES = new StaticMovieListRepository().getMovies();

    @Before
    public void setUp() throws Exception {
        movieListPresenter = new MovieListPresenter(movieListView, movieListRepository);
    }

    @Test
    public void shouldShowMoviesList() {
        when(movieListRepository.getMovies()).thenReturn(SOME_MOVIES);

        movieListPresenter.displayMovies();

        verify(movieListView).displayMoviesList(SOME_MOVIES);
    }

    @Test
    public void shouldShowNoMoviesList() {
        when(movieListRepository.getMovies()).thenReturn(EMPTY_LIST);

        movieListPresenter.displayMovies();

        verify(movieListView).displayNoMoviesMessage();
    }

    @Test
    public void shouldDisplaySelectedMovie() {
        when(movieListRepository.getMovies()).thenReturn(SOME_MOVIES);

        int clickedPosition = 1;

        Movie clickedMovie = SOME_MOVIES.get(clickedPosition);

        movieListPresenter.displayMovies();

        movieListPresenter.movieClicked(clickedPosition);

        verify(movieListView).displayMovieDetail(clickedMovie);
    }
}