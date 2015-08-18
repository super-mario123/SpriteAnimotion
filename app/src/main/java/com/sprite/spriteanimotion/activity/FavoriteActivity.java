package com.sprite.spriteanimotion.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.adapter.FavoriteAdapter;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.utils.DBManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dings on 2015/8/17.
 */
public class FavoriteActivity extends AppCompatActivity {

    @Bind(R.id.fav_swipe_layout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.fav_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fav_toolbar)
    Toolbar mToolbar;

    private RecyclerView.LayoutManager mLayoutManager;
    private FavoriteAdapter mAdapter;
    private List<Movie> mMovies;
    private Context mContext;
    private DBManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);
        mContext = this;
        mManager = new DBManager(mContext);
        initRecyclerView();
        initRefreshLayout();
        initToolbar();
    }

    public void initToolbar(){
        mToolbar.setTitle("收藏列表");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteActivity.this.onBackPressed();
            }
        });
    }

    public void initRecyclerView(){
        mLayoutManager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new FavoriteAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initRefreshLayout(){
        mRefreshLayout.setColorSchemeColors(R.color.primary_dark,R.color.primary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMovies = mManager.query();
                mAdapter.clearMovies();
                mAdapter.addMovies(mMovies);
                mRefreshLayout.setRefreshing(false);
            }
        });
        mRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
                        getResources().getDisplayMetrics()));
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mManager.closeDB();
    }
}
