package com.sprite.spriteanimotion.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.adapter.FavoriteAdapter;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.utils.DBManager;
import com.sprite.spriteanimotion.views.MovieItemTouchHelperCallback;
import com.sprite.spriteanimotion.views.OnStartDragListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dings on 2015/8/17.
 */
public class FavoriteActivity extends AppCompatActivity implements OnStartDragListener {

    @Bind(R.id.fav_swipe_layout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.fav_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fav_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.fav_empty_textView)
    TextView mEmptyView;

    private RecyclerView.LayoutManager mLayoutManager;
    private FavoriteAdapter mAdapter;
    private List<Movie> mMovies;
    private Context mContext;
    private DBManager mManager;
    private ItemTouchHelper mItemTouchHelper;

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

    public void initToolbar() {
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

    public void initRecyclerView() {
        mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new FavoriteAdapter(mContext,this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new MovieItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public void initRefreshLayout() {
        mRefreshLayout.setColorSchemeColors(R.color.primary_dark, R.color.primary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMovies = mManager.query();
//                if (mMovies == null) {
//                    mRecyclerView.setVisibility(View.GONE);
//                    mEmptyView.setVisibility(View.VISIBLE);
//                } else {
//                    mRecyclerView.setVisibility(View.VISIBLE);
//                    mEmptyView.setVisibility(View.GONE);
//                }
                mAdapter.clearMovies();
                mAdapter.addMovies(mMovies);
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fav_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        mManager.closeDB();
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
