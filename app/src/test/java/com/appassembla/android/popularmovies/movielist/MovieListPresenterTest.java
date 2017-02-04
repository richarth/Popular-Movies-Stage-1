package com.appassembla.android.popularmovies.movielist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by richardthompson on 04/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieListPresenterTest {

    @Mock
    private MovieListView movieListView;
    @Mock
    private MovieListRepository movieListRepository;
    private MovieListPresenter movieListPresenter;

    private static final List<Movie> SOME_MOVIES = Arrays.asList(new Movie(), new Movie(), new Movie());

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
}