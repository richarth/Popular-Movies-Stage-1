package com.appassembla.android.popularmovies.movielist;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.MoviesRepository;
import com.appassembla.android.popularmovies.data.WebMoviesRepository;
import com.appassembla.android.popularmovies.moviedetail.MovieDetailsActivity;
import com.appassembla.android.popularmovies.moviedetail.MovieDetailsFragment;
import com.appassembla.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.*;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailsActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity implements MovieListView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @BindView(R.id.movie_list)
    protected RecyclerView recyclerView;

    protected MovieListPresenter movieListPresenter;

    @BindView(R.id.no_movies_message)
    protected TextView noMoviesTextView;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Nullable
    @BindView(R.id.movie_detail_container)
    protected FrameLayout movieDetailContainer;

    private final static String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    private static final int NUM_COLUMNS_IN_LIST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ButterKnife.bind(this);

        setupToolbar();

        checkIfInTwoPaneMode();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        setupPresenter();

        movieListPresenter.displayMovies(MoviesRepository.POPULAR_SORT_TYPE);

        restoreRecyclerViewState();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, @NonNull List<Movie> movies) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(movies));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MovieListActivity.this, NUM_COLUMNS_IN_LIST);

        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void setupPresenter() {
        movieListPresenter = new MovieListPresenter(this, new WebMoviesRepository());
    }

    private void checkIfInTwoPaneMode() {
        if (movieDetailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public void displayMoviesList(@NonNull List<Movie> movies) {
        recyclerView.setVisibility(VISIBLE);
        noMoviesTextView.setVisibility(INVISIBLE);

        setupRecyclerView(recyclerView, movies);
    }

    @Override
    public void displayNoMoviesMessage() {
        recyclerView.setVisibility(INVISIBLE);
        noMoviesTextView.setVisibility(VISIBLE);
    }

    @Override
    public void displayMovieDetail(int moviePositionInRepository, int moviePositionInAdapter) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(MovieDetailsFragment.ARG_ITEM_ID, moviePositionInRepository);
            MovieDetailsFragment fragment = new MovieDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra(MovieDetailsFragment.ARG_ITEM_ID, moviePositionInRepository);

            String transName = getString(R.string.detail_transition);

            SimpleItemRecyclerViewAdapter.ViewHolder currentPositionViewHolder = (SimpleItemRecyclerViewAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(moviePositionInAdapter);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MovieListActivity.this, currentPositionViewHolder.posterView, transName);

            // Transitions are only supported from Jelly Bean and up
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, optionsCompat.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }

    private void restoreRecyclerViewState() {
        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    private void saveRecyclerViewState() {
        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveRecyclerViewState();
    }

    @Override
    protected void onStop() {
        super.onStop();

        movieListPresenter = null;
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Movie> mValues;

        public SimpleItemRecyclerViewAdapter(List<Movie> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Picasso.with(getApplication()).load(Movie.posterImgBaseUrl + mValues.get(position).posterUrl()).into(holder.posterView);
            holder.posterView.setContentDescription(mValues.get(position).name());

            int clickedPosition = holder.getAdapterPosition();

            holder.mView.setOnClickListener(v -> movieListPresenter.movieClicked(mValues.get(clickedPosition).id(), clickedPosition));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            @BindView(R.id.movie_poster)
            ImageView posterView;

            public ViewHolder(View view) {
                super(view);

                ButterKnife.bind(this, view);

                mView = view;
            }
        }
    }
}
