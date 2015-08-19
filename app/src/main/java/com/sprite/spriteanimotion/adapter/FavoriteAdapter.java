package com.sprite.spriteanimotion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.activity.MovieDetailActivity;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.task.MovieTask;
import com.sprite.spriteanimotion.utils.DBManager;
import com.sprite.spriteanimotion.views.ItemTouchHelperViewHolder;
import com.sprite.spriteanimotion.views.ItemTouchListener;
import com.sprite.spriteanimotion.views.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by dings on 2015/8/17.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> implements ItemTouchListener {

    private Context mContext;
    private List<Movie> mMovies;
    private DBManager mManager;
    private OnStartDragListener mStartDragListener;

    public FavoriteAdapter(Context context, OnStartDragListener onStartDragListener) {
        mContext = context;
        mStartDragListener = onStartDragListener;
        mManager = new DBManager(context);
        mMovies = mManager.query();
    }

    public void addMovies(List<Movie> movieList) {
        mMovies.addAll(movieList);
        notifyItemRangeChanged(0, movieList.size() - 1);
    }

    public void clearMovies() {
        int size = mMovies.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mMovies.remove(0);
            }
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final FavoriteViewHolder holder, final int position) {
        final Movie movie = mMovies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieOriginalTitle.setText(movie.getOriginal_title());
        MovieTask task = new MovieTask(mContext);
        task.setDataFinishListener(new MovieTask.GetDataFinishListener() {
            @Override
            public void onDataFinish(Object data) {
                Movie movie1 = (Movie) data;
                if (movie1 != null) {
                    Glide.with(mContext).load(movie1.getImages().getSmall()).into(holder.movieCover);
                }
            }
        });

        task.execute(movie.getMovieId());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("movieID", movie.getMovieId());
                mContext.startActivity(intent);
            }
        });

        holder.movieCover.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.primary_dark));
//                Snackbar.make(mCardView,"longClicked",Snackbar.LENGTH_SHORT).show();
//                AlertDialog dialog = new AlertDialog.Builder(mContext).setTitle("是否要删除")
//                        .setPositiveButton("确定", new AlertDialog.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                mManager.deleteMovie(movie);
//                                notifyItemChanged(holder.getLayoutPosition());
//                            }
//                        }).setNegativeButton("取消", new AlertDialog.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).show();
//                return false;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mMovies, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mMovies, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mManager.deleteMovie(mMovies.get(position));
        mMovies.remove(position);
        notifyItemRemoved(position);
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        ImageView movieCover;
        TextView movieTitle;
        TextView movieOriginalTitle;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            movieCover = (ImageView) itemView.findViewById(R.id.iv_movie_cover);
            movieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            movieOriginalTitle = (TextView) itemView.findViewById(R.id.tv_movie_original_title);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(mContext.getResources().getColor(R.color.primary_dark));
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0xfff);
        }
    }
}
