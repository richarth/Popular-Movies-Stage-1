package com.appassembla.android.popularmovies.movielist;

import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;
import com.appassembla.android.popularmovies.data.StaticMoviesRepository;

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
    private MoviesRepository moviesRepository;
    private MovieListPresenter movieListPresenter;

    private static final List<Movie> SOME_MOVIES = new StaticMoviesRepository().getMovies(MoviesRepository.POPULAR_SORT_TYPE);

    private static final int INVALID_SORT_TYPE = 0;

    @Before
    public void setUp() {
        movieListPresenter = new MovieListPresenter(movieListView, moviesRepository);
    }

    @Test
    public void shouldShowMoviesList() {
        when(moviesRepository.getMovies(MoviesRepository.POPULAR_SORT_TYPE)).thenReturn(SOME_MOVIES);

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        verify(movieListView).displayMoviesList(SOME_MOVIES);
    }

    @Test
    public void shouldHideSpinnerAfterMoviesFound() {
        when(moviesRepository.getMovies(MoviesRepository.POPULAR_SORT_TYPE)).thenReturn(SOME_MOVIES);

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        verify(movieListView).hideProgressBar();
    }

    @Test
    public void shouldShowNoMoviesList() {
        when(moviesRepository.getMovies(MoviesRepository.POPULAR_SORT_TYPE)).thenReturn(EMPTY_LIST);

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        verify(movieListView).displayNoMoviesMessage();
    }

    @Test
    public void shouldHideSpinnerAfterNoMoviesFound() {
        when(moviesRepository.getMovies(MoviesRepository.POPULAR_SORT_TYPE)).thenReturn(EMPTY_LIST);

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        verify(movieListView).displayNoMoviesMessage();
    }

    @Test
    public void shouldDisplaySelectedMovie() {
        when(moviesRepository.getMovies(MoviesRepository.POPULAR_SORT_TYPE)).thenReturn(SOME_MOVIES);

        int clickedPosition = 1;

        int adapterPosition = 2;

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        movieListPresenter.movieClicked(clickedPosition, adapterPosition);

        verify(movieListView).displayMovieDetail(clickedPosition, adapterPosition);
    }

    @Test
    public void shouldShowNoMoviesForInvalidSortType() {
        when(moviesRepository.getMovies(INVALID_SORT_TYPE)).thenReturn(EMPTY_LIST);

        movieListPresenter.displayMovies(INVALID_SORT_TYPE);

        verify(movieListView).displayNoMoviesMessage();
    }
}