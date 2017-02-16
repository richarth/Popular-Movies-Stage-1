package com.appassembla.android.popularmovies.moviedetail;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    @BindView(R.id.detail_poster_image_view)
    protected ImageView posterImageView;
    @BindView(R.id.release_date_text_view)
    protected TextView releaseDateTextView;
    @BindView(R.id.user_rating_text_view)
    protected TextView averageRatingTextView;
    @BindView(R.id.plot_synopsis_text_view)
    protected TextView synopsisTextView;
    private ImageView heroImage;
    private CollapsingToolbarLayout appBarLayout;

    private Unbinder unbinder;

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

        unbinder = ButterKnife.bind(this, rootView);

        Activity activity = this.getActivity();

        appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

        heroImage = (ImageView) activity.findViewById(R.id.backdrop);

        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (movieDetailsPresenter != null) {
            movieDetailsPresenter.displayMovie();
        }
    }

    private void setToolbarTitle(String movieName) {
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

        Picasso.with(getActivity()).load(selectedMovie.getPosterImgFullUrl()).into(posterImageView);

        posterImageView.setContentDescription(selectedMovie.name());

        BitmapDrawable posterDrawable = (BitmapDrawable) posterImageView.getDrawable();

        if (posterDrawable != null) {

            Bitmap posterBitmap = posterDrawable.getBitmap();

            Palette posterPalette = createPaletteSync(posterBitmap);

            colourStatusBar(posterPalette);

            colourToolbar(posterPalette);
        }

        Picasso.with(getActivity()).load(selectedMovie.getHeroImgFullUrl()).into(heroImage);

        heroImage.setContentDescription(selectedMovie.name());

        releaseDateTextView.setText(selectedMovie.getMovieYear());

        String averageRating = Double.toString(selectedMovie.averageRating());
        averageRatingTextView.setText(averageRating);

        synopsisTextView.setText(selectedMovie.plotSynopsis());
    }

    private void colourToolbar(Palette p) {
        Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

        // if we got a vibrant swatch set the toolbar colours
        if (vibrantSwatch != null) {
            // Set the toolbar background and text colors
            appBarLayout.setBackgroundColor(vibrantSwatch.getRgb());
            //appBarLayout.setTitleTextColor(vibrantSwatch.getTitleTextColor());
        }
    }

    private void colourStatusBar(Palette p) {
        Palette.Swatch darkMutedSwatch = p.getDarkMutedSwatch();

        // if we got a dark muted swatch set the status bar colour.
        // We can only set the status bar colour from lollipop and up
        if (darkMutedSwatch != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(darkMutedSwatch.getRgb());
        }
    }

    private Palette createPaletteSync(Bitmap bitmap) {
        Palette p = Palette.from(bitmap).generate();
        return p;
    }

    @Override
    public void onStop() {
        super.onStop();

        movieDetailsPresenter = null;
    }
}
