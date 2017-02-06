package com.appassembla.android.popularmovies.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by richardthompson on 05/02/2017.
 */
public class StaticMoviesRepositoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldGetMovieWithId7() throws Exception {
        Movie testMovie = Movie.create(7, "Movie 7", "", "", 0, new Date());

        MoviesRepository moviesRepository = new StaticMoviesRepository();

        Movie selectedMovie = moviesRepository.getMovie(7);

        assertEquals(testMovie, selectedMovie);
    }

    @Test
    public void shouldGetNoMovieWhenIdDoesntExist() throws Exception {
        MoviesRepository moviesRepository = new StaticMoviesRepository();

        Movie selectedMovie = moviesRepository.getMovie(56);

        assertNull(selectedMovie);
    }

}