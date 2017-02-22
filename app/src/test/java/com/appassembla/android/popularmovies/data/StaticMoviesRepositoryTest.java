package com.appassembla.android.popularmovies.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import retrofit2.HttpException;

import static io.reactivex.Single.just;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by richardthompson on 05/02/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class StaticMoviesRepositoryTest {

    private MoviesRepository moviesRepository;

    @Mock
    private MoviesRepository emptyMoviesRepository;

    @Mock
    private HttpException error404;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        moviesRepository = new StaticMoviesRepository();
    }

    @Test
    public void shouldGetMovieWithId7() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "1979-05-04", "http://i.imgur.com/DvpvklR.png");

        Movie selectedMovie = moviesRepository.getMovieById(7).blockingGet();

        assertEquals(testMovie, selectedMovie);
    }

    @Test
    public void shouldGetNoMovieWhenIdDoesntExist() {
        when(emptyMoviesRepository.getMovieById(56)).thenReturn(Single.error(error404));

        TestObserver<Movie> testObserver = new TestObserver<>();

        emptyMoviesRepository.getMovieById(56).subscribe(testObserver);

        testObserver.assertError(HttpException.class);
    }

    @Test
    public void shouldGetMovieYear() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "1979-05-04", "http://i.imgur.com/DvpvklR.png");

        String movieYear = testMovie.getMovieYear();

        assertEquals("1979", movieYear);
    }

    @Test
    public void shouldGetEmptyMovieYear() {
        Movie testMovie = Movie.create(7, "Movie 7", "http://i.imgur.com/DvpvklR.png", "Movie 7 is about Jedi", 0, "", "http://i.imgur.com/DvpvklR.png");

        String movieYear = testMovie.getMovieYear();

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