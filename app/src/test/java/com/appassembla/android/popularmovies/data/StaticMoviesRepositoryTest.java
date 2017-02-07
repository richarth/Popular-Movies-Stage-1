package com.appassembla.android.popularmovies.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by richardthompson on 05/02/2017.
 */
public class StaticMoviesRepositoryTest {

    private MoviesRepository moviesRepository;

    @Before
    public void setUp() throws Exception {
        moviesRepository = new StaticMoviesRepository();
    }

    @Test
    public void shouldGetMovieWithId7() throws Exception {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, new Date());

        Movie selectedMovie = moviesRepository.getMovieById(7);

        assertEquals(testMovie, selectedMovie);
    }

    @Test
    public void shouldGetNoMovieWhenIdDoesntExist() throws Exception {
        Movie selectedMovie = moviesRepository.getMovieById(56);

        assertNull(selectedMovie);
    }

}