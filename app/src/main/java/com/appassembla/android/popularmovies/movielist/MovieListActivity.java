package com.appassembla.android.popularmovies.movielist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.appassembla.android.popularmovies.data.Movie;
import com.appassembla.android.popularmovies.data.StaticMoviesRepository;
import com.appassembla.android.popularmovies.moviedetail.MovieDetailsActivity;
import com.appassembla.android.popularmovies.moviedetail.MovieDetailsFragment;
import com.appassembla.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    private RecyclerView recyclerView;

    private MovieListPresenter movieListPresenter;
    private TextView noMoviesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setupToolbar();

        findRecyclerView();

        findNoMoviesMessage();

        checkIfInTwoPaneMode();

        setupPresenter();

        movieListPresenter.displayMovies();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
    }

    private void findRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.movie_list);
        assert recyclerView != null;
    }

    private void findNoMoviesMessage() {
        noMoviesTextView = (TextView) findViewById(R.id.no_movies_message);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, @NonNull List<Movie> movies) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(movies));
    }

    private void setupPresenter() {
        movieListPresenter = new MovieListPresenter(this, new StaticMoviesRepository());
    }

    private void checkIfInTwoPaneMode() {
        if (findViewById(R.id.movie_detail_container) != null) {
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
    public void displayMovieDetail(int moviePositionInRepository) {
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

            startActivity(intent);
        }
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
            Picasso.with(getApplication()).load(mValues.get(position).posterUrl()).into(holder.posterView);
            holder.posterView.setContentDescription(mValues.get(position).name());

            holder.mView.setOnClickListener(v -> movieListPresenter.movieClicked(mValues.get(holder.getAdapterPosition()).id()));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final ImageView posterView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                posterView = (ImageView) view.findViewById(R.id.movie_poster);
            }
        }
    }
}
