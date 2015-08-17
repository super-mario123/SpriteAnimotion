package com.sprite.spriteanimotion.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.adapter.TopMovieAdapter;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.task.TopMovieTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dings on 2015/8/5.
 */
public class TopMovieFragment extends Fragment {

    private static final String TAG = "TopMovieFragment";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;
    private TopMovieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> mMovieList;
    private static Activity mActivity;
    private FloatingActionButton mFab_refresh;

    public static TopMovieFragment newInstance(Activity activity) {
        mActivity = activity;
        return new TopMovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout);
        mFab_refresh = (FloatingActionButton) rootView.findViewById(R.id.main_fab);
        initView();
        refresh();
        return rootView;
    }

    private void refresh() {
        TopMovieTask task = new TopMovieTask(getActivity());
        task.setDataFinishRefresh(new TopMovieTask.GetDataFinishRefresh() {
            @Override
            public void onDataFinish(Object data) {
                mMovieList = (ArrayList<Movie>) data;
                mAdapter.addMovies(mMovieList);
                mRecyclerView.setAdapter(mAdapter);
                mRefreshLayout.setRefreshing(false);
            }
        });
        task.execute();
    }

    public void initView() {
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new TopMovieAdapter(getActivity(), mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setColorSchemeColors(R.color.md_blue_700);
        refreshing();
        mFab_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshing();
            }
        });
        mRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        mRefreshLayout.setRefreshing(true);
    }

    private void refreshing() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                TopMovieTask task = new TopMovieTask(getActivity());
                task.setDataFinishRefresh(new TopMovieTask.GetDataFinishRefresh() {
                    @Override
                    public void onDataFinish(Object data) {
                        mMovieList = (ArrayList<Movie>) data;
                        mAdapter.clearMovies();
                        mAdapter.addMovies(mMovieList);
                        mRefreshLayout.setRefreshing(false);
                    }
                });
                task.execute();
            }
        });
    }
}
