package com.sprite.spriteanimotion.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.adapter.TopMovieAdapter;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.task.MovieInTheatersTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dings on 2015/8/10.
 */
public class MovieInTheaterFragment extends Fragment {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private TopMovieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> mMovies;
    private static Activity sActivity;
    private FloatingActionButton mFab_refresh;

    public static MovieInTheaterFragment newInstance(Activity activity) {
        sActivity = activity;
        return new MovieInTheaterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout);
        mFab_refresh = (FloatingActionButton) rootView.findViewById(R.id.main_fab);
        init();
        MovieInTheatersTask task = new MovieInTheatersTask(getActivity());
        task.setDataFinishListener(new MovieInTheatersTask.GetDataFinishListener() {
            @Override
            public void onDataFinish(Object data) {
                mMovies = (ArrayList<Movie>) data;
                mAdapter.addMovies(mMovies);
                mRecyclerView.setAdapter(mAdapter);
                mRefreshLayout.setRefreshing(false);
            }
        });
        task.execute();
        return rootView;
    }

    public void init() {
        mAdapter = new TopMovieAdapter(getActivity(), sActivity);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setColorSchemeColors(R.color.md_blue_700);
        refresh();
        mFab_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        mRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        mRefreshLayout.setRefreshing(true);
    }

    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * refresh the data
     */
    private void refresh() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MovieInTheatersTask task = new MovieInTheatersTask(getActivity());
                task.setDataFinishListener(new MovieInTheatersTask.GetDataFinishListener() {
                    @Override
                    public void onDataFinish(Object data) {
                        mMovies = (ArrayList<Movie>) data;
                        mAdapter.clearMovies();
                        mAdapter.addMovies(mMovies);
                        mRefreshLayout.setRefreshing(false);
                    }
                });
                task.execute();
            }
        });
    }
}
