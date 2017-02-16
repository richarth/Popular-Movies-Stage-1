package com.appassembla.android.popularmovies.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by richardthompson on 05/02/2017.
 */
@RunWith(JUnit4.class)
public class StaticMoviesRepositoryTest {

    private MoviesRepository moviesRepository;

    @Before
    public void setUp() {
        moviesRepository = new StaticMoviesRepository();
    }

    @Test
    public void shouldGetMovieWithId7() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "1979-05-04", "http://i.imgur.com/DvpvklR.png");

        Movie selectedMovie = moviesRepository.getMovieById(7);

        assertEquals(testMovie, selectedMovie);
    }

    @Test
    public void shouldGetNoMovieWhenIdDoesntExist() {
        Movie selectedMovie = moviesRepository.getMovieById(56);

        assertNull(selectedMovie);
    }

    @Test
    public void shouldGetMovieYear() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "1979-05-04", "http://i.imgur.com/DvpvklR.png");

        String movieYear = testMovie.geMovieYear();

        assertEquals("1979", movieYear);
    }

    @Test
    public void shouldGetEmptyMovieYear() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "", "http://i.imgur.com/DvpvklR.png");

        String movieYear = testMovie.geMovieYear();

        assertEquals("", movieYear);
    }

    @Test
    public void shouldGetValidPosterImgUrl() {
        Movie testMovie = Movie.create(7, "Movie 7", "/DvpvklR.png", "Movie 7 is about Jedi", 0, "", "/DvpvklR2.png");

        String posterUrl = testMovie.getPosterImgFullUrl();

        assertEquals("http://image.tmdb.org/t/p/w500/DvpvklR.png", posterUrl);
    }

    @Test
    public void shouldGetValidHeroImgUrl() {
        Movie testMovie = Movie.create(7, "Movie 7", "/DvpvklR.png", "Movie 7 is about Jedi", 0, "", "/DvpvklR2.png");

        String posterUrl = testMovie.getHeroImgFullUrl();

        assertEquals("http://image.tmdb.org/t/p/w780/DvpvklR2.png", posterUrl);
    }

}