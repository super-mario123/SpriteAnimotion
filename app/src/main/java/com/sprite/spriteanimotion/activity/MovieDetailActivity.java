package com.sprite.spriteanimotion.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette.Swatch;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.db.MovieDB;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.model.SimpleCelebrity;
import com.sprite.spriteanimotion.task.MovieTask;
import com.sprite.spriteanimotion.utils.DBManager;
import com.sprite.spriteanimotion.utils.EventUtil;
import com.sprite.spriteanimotion.utils.GUIUtils;
import com.sprite.spriteanimotion.utils.ORMUtil;
import com.sprite.spriteanimotion.views.ObservableScrollView;
import com.sprite.spriteanimotion.views.ScrollViewListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by dings on 2015/8/5.
 */
public class MovieDetailActivity extends AppCompatActivity implements ScrollViewListener {

    private static final String TAG = "MovieDetailActivity";
    @Bind(R.id.activity_detail_scroll) ObservableScrollView mScrollView;
    @Bind(R.id.activity_detail_cover) ImageView mMovieCover;
    @Bind(R.id.activity_detail_fab) FloatingActionButton mCollectButton;
    @Bind(R.id.activity_detail_title) TextView mMovieTitle;
    @Bind(R.id.activity_detail_rating) RatingBar mRating;
    @Bind(R.id.activity_detail_director) TextView mDirector;
    @Bind(R.id.activity_detail_actor) TextView mActors;
    @Bind(R.id.activity_detail_type) TextView mMovieType;
    //@Bind(R.id.activity_detail_website) TextView mWebsite;
    @Bind(R.id.activity_detail_description) TextView mMovieDescription;
    @Bind(R.id.activity_detail_year) TextView mMovieYears;

    private Swatch mBrightSwatch;
    private Movie movie;
    private DBManager mManager;
    private Context mContext;
    private boolean isCollected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        mContext = this;
        mScrollView.setViewListener(this);
        initDetail();
//        MovieDB movieDB = new MovieDB(this,MovieDB.NAME,null,MovieDB.VERSION);
        mManager = new DBManager(this);
        GUIUtils.makeTheStatusbarTranslucent(this);
        EventBus.getDefault().register(this);
    }


    public void initDetail() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String movieID = bundle.getString("movieID");
        MovieTask task = new MovieTask(this);
        task.setDataFinishListener(new MovieTask.GetDataFinishListener() {
            @Override
            public void onDataFinish(Object data) {
                movie = (Movie) data;
                if (movie != null) {
                    Glide.with(MovieDetailActivity.this).load(movie.getImages().getLarge()).into(mMovieCover);
                    mMovieTitle.setText(movie.getTitle());
                    mMovieDescription.setText(movie.getSummary());
                    String types = "";
                    for (int i = 0; i < movie.getGenres().length; i++) {
                        String[] genres = movie.getGenres();
                        types += genres[i] + "  ";
                    }
                    mMovieType.setText(types);
                    mMovieYears.setText(movie.getYear() + "年");
                    //if (movie.getWebsite() != null) {
                    //    mWebsite.setText(movie.getWebsite());
                    //}
                    String directors = "";
                    for (int i = 0; i < movie.getDirectors().length; i++) {
                        SimpleCelebrity[] directorsName = movie.getDirectors();
                        directors += directorsName[i].getName() + "  ";
                    }
                    mDirector.setText(directors);
                    String actors = "";
                    for (int i = 0; i < movie.getCasts().length; i++) {
                        SimpleCelebrity[] actorsName = movie.getCasts();
                        actors += actorsName[i].getName() + "  ";
                    }
                    mActors.setText(actors);
                    mRating.setStepSize(0.5f);
                    mRating.setRating(movie.getRating().getStars() / 10f);
                    Log.d(TAG,movie.getRating().getStars() + "");

                    mCollectButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Snackbar.make(mScrollView, "收藏成功",
                                Snackbar.LENGTH_SHORT).show();
                            //GUIUtils.showViewByScale(mMovieCover).start();
//                            ORMUtil.saveMovie(movie);
                            for (Movie qmovie: new DBManager(mContext).query()) {
                                if (qmovie.getMovieId().equals(movie.getMovieId())) {
                                    isCollected = true;
                                }
                            }

                            if (! isCollected){
                                    mManager.addMovie(movie);
                                }else{
                                Snackbar.make(mScrollView, "已在收藏列表",
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        task.execute(movieID);
    }

    boolean isTranslucent = false;

    @Override
    public void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y > mMovieCover.getHeight()) {
            mMovieTitle.setTranslationY(y - mMovieCover.getHeight());
            if (!isTranslucent) {
                isTranslucent = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    GUIUtils.setTheStatusbarNotTranslucent(this);
//                    getWindow().setStatusBarColor(mBrightSwatch.getRgb());
                }
            }
        }

        if (y < mMovieCover.getHeight() && isTranslucent) {
            mMovieTitle.setTranslationY(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                GUIUtils.makeTheStatusbarTranslucent(this);
                isTranslucent = false;
            }
        }
    }

    @Subscribe
    public void onEvent(EventUtil event){
        String msg = "Received the event.  "+event.getMsg();
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        mMovieTitle.setText("事件总线");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
