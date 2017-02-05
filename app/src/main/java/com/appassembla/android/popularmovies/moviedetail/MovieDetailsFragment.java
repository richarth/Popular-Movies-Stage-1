package com.appassembla.android.popularmovies.moviedetail;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appassembla.android.popularmovies.R;
import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.movielist.MovieListActivity;
import com.appassembla.android.popularmovies.data.MoviesRepository;
import com.appassembla.android.popularmovies.data.StaticMoviesRepository;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailsActivity}
 * on handsets.
 */
public class MovieDetailsFragment extends Fragment implements MovieDetailsView {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private MovieDetailsPresenter movieDetailsPresenter;

    private TextView movieTitleTextView;

    public MovieDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            setupPresenter(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the dummy content as text in a TextView.
        movieTitleTextView = (TextView) rootView.findViewById(R.id.movie_detail);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (movieDetailsPresenter != null) {
            movieDetailsPresenter.displayMovie();
        }
    }

    public void setToolbarTitle(String movieName) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(movieName);
        }
    }

    private void setupPresenter(int selectedMovieId) {
        movieDetailsPresenter = new MovieDetailsPresenter(this, new StaticMoviesRepository(), selectedMovieId);
    }

    @Override
    public void displayMovieDetails(@NonNull Movie selectedMovie) {
        setToolbarTitle(selectedMovie.name());

        movieTitleTextView.setText(selectedMovie.name());
    }
}
