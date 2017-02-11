package com.appassembla.android.popularmovies.moviedetail;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appassembla.android.popularmovies.R;
import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.WebMoviesRepository;
import com.appassembla.android.popularmovies.movielist.MovieListActivity;
import com.squareup.picasso.Picasso;

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
    private ImageView posterImageView;
    private TextView releaseDateTextView;
    private TextView averageRatingTextView;
    private TextView synopsisTextView;

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

        movieTitleTextView = (TextView) rootView.findViewById(R.id.movie_detail);
        posterImageView = (ImageView) rootView.findViewById(R.id.detail_poster_image_view);
        releaseDateTextView = (TextView) rootView.findViewById(R.id.release_date_text_view);
        averageRatingTextView = (TextView) rootView.findViewById(R.id.user_rating_text_view);
        synopsisTextView = (TextView) rootView.findViewById(R.id.plot_synopsis_text_view);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (movieDetailsPresenter != null) {
            movieDetailsPresenter.displayMovie();
        }
    }

    private void setToolbarTitle(String movieName) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(movieName);
        }
    }

    private void setupPresenter(int selectedMovieId) {
        movieDetailsPresenter = new MovieDetailsPresenter(this, new WebMoviesRepository(), selectedMovieId);
    }

    @Override
    public void displayMovieDetails(@NonNull Movie selectedMovie) {
        setToolbarTitle(selectedMovie.name());

        movieTitleTextView.setText(selectedMovie.name());

        Picasso.with(getActivity()).load(Movie.posterImgBaseUrl + selectedMovie.posterUrl()).into(posterImageView);

        releaseDateTextView.setText(selectedMovie.releaseDate());

        String averageRating = Double.toString(selectedMovie.averageRating());
        averageRatingTextView.setText(averageRating);

        synopsisTextView.setText(selectedMovie.plotSynopsis());
    }

    @Override
    public void onStop() {
        super.onStop();

        movieDetailsPresenter = null;
    }
}
