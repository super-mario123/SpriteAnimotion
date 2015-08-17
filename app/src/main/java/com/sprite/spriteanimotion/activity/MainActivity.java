package com.sprite.spriteanimotion.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.adapter.MovieFragmentAdapter;
import com.sprite.spriteanimotion.fragment.MovieInTheaterFragment;
import com.sprite.spriteanimotion.fragment.TopMovieFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private NavigationView mNavMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setupViewPager();
        setNavMenu();
    }

    public void setNavMenu(){
        mNavMenu = (NavigationView) findViewById(R.id.main_nav_view);
        mNavMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "主页", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_download:
                        Toast.makeText(MainActivity.this, "离线下载", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_collect:
                        Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,FavoriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_history:
                        Toast.makeText(MainActivity.this, "历史记录", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_foucs:
                        Toast.makeText(MainActivity.this, "我的关注", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_account:
                        Toast.makeText(MainActivity.this, "消费记录", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_help:
                        Toast.makeText(MainActivity.this, "帮助", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void setupViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        TopMovieFragment topMovieFragment = TopMovieFragment.newInstance(this);
        MovieInTheaterFragment movieInTheaterFragment = MovieInTheaterFragment.newInstance(this);
        MovieFragmentAdapter fragmentAdapter = new MovieFragmentAdapter(getSupportFragmentManager(),this);
        fragmentAdapter.addFragments(movieInTheaterFragment);
        fragmentAdapter.addFragments(topMovieFragment);
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ic_menu_search) {
            Snackbar.make(mDrawerLayout,"暂不支持搜索",Snackbar.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
