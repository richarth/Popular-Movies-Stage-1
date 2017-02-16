package com.appassembla.android.popularmovies.data;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by richardthompson on 10/02/2017.
 */

@MoshiAdapterFactory
abstract class MoviesListingAdapterFactory implements JsonAdapter.Factory {
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_MoviesListingAdapterFactory();
    }
}