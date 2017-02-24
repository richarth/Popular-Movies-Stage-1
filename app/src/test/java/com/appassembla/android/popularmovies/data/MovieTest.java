package com.appassembla.android.popularmovies.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by richardthompson on 19/02/2017.
 */
@SuppressWarnings({"unused", "DefaultFileTemplate"})
@RunWith(JUnit4.class)
public class MovieTest {

    @Test
    public void shouldSortByHighestRated() {
        int selectedSpinnerPosition = 1;

        int sortOrderSelected = Movie.determineDesiredSortOrder(selectedSpinnerPosition);

        assertEquals(MoviesRepository.TOP_RATED_SORT_TYPE, sortOrderSelected);
    }

    @Test
    public void shouldSortByMostPopular() {
        int selectedSpinnerPosition = 0;

        int sortOrderSelected = Movie.determineDesiredSortOrder(selectedSpinnerPosition);

        assertEquals(MoviesRepository.POPULAR_SORT_TYPE, sortOrderSelected);
    }

}