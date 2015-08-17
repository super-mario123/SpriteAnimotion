package com.sprite.spriteanimotion.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.activity.MovieDetailActivity;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.utils.EventUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by dings on 2015/8/5.
 */
public class TopMovieAdapter extends RecyclerView.Adapter<TopMovieAdapter.MovieViewHolder> {

    private static final String TAG = "TopMovieAdapter";
    private static final String SHARED_ELEMENT_COVER = "cover";
    private static final String EXTRA_MOVIE_LOCATION = "view_location";
    private Context mContext;
    private List<Movie> mMovieList;
    private Activity mActivity;


    public TopMovieAdapter(Context context, Activity activity) {
        mActivity = activity;
        mContext = context;
        mMovieList = new ArrayList<>();
    }

    public void addMovies(List<Movie> movieList) {
        mMovieList.addAll(movieList);
        notifyItemRangeChanged(0, movieList.size() - 1);
    }

    public void clearMovies() {
        int size = mMovieList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mMovieList.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder movieViewHolder, final int i) {
        final Movie movie = mMovieList.get(i);
        movieViewHolder.movieTitle.setText(movie.getTitle());

        Picasso.with(mContext).load(movie.getImages().getLarge()).into(movieViewHolder.movieCover);

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("movieID", movie.getMovieId());
                intent.putExtras(bundle);
                EventBus.getDefault().post(new EventUtil("Post the event."));
                startDetailActivityByAnimation(movieViewHolder.itemView, movieViewHolder.touchx,
                        movieViewHolder.touchy, intent);
            }
        });
    }


    private void startDetailActivityByAnimation(View touchedView,
                                                int touchedX, int touchedY, Intent movieDetailActivityIntent) {

        int[] touchedLocation = {touchedX, touchedY};
        int[] locationAtScreen = new int[2];
        touchedView.getLocationOnScreen(locationAtScreen);

        int finalLocationX = locationAtScreen[0] + touchedLocation[0];
        int finalLocationY = locationAtScreen[1] + touchedLocation[1];

        int[] finalLocation = {finalLocationX, finalLocationY};
        movieDetailActivityIntent.putExtra(EXTRA_MOVIE_LOCATION,
                finalLocation);

        mContext.startActivity(movieDetailActivityIntent);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startDetailActivityBySharedElements(Activity activity, View touchView,
                                                     int moviePosition, Intent movieDetailActivityIntent) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                activity, new Pair<>(touchView, SHARED_ELEMENT_COVER + moviePosition)
        );
        mContext.startActivity(movieDetailActivityIntent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public static float px2dp(Context context, float pxVal)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

        int touchx, touchy;
        ImageView movieCover;
        TextView movieTitle;
//        RecyclerView.LayoutParams params;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieCover = (ImageView) itemView.findViewById(R.id.item_movie_cover);
            movieTitle = (TextView) itemView.findViewById(R.id.item_movie_title);
//            int height = (int) (Math.random() * 200 + 500);
//            Log.d(TAG,px2dp(mContext,height) + "");
//            params = new StaggeredGridLayoutManager.LayoutParams(itemView.getWidth(), height);
//            itemView.setLayoutParams(params);
//            itemView.setPadding(150, 150, 150, 150);
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {
                touchx = (int) event.getX();
                touchy = (int) event.getY();
            }
            return false;
        }
    }
}
