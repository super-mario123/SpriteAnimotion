package com.sprite.spriteanimotion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dings on 2015/8/17.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;

    public FavoriteAdapter(Context context){
        mContext = context;
        mMovies = new DBManager(context).query();
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
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);

        return new FavoriteViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final FavoriteViewHolder holder, int position) {
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
                intent.putExtra("movieID",movie.getMovieId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{

        ImageView movieCover;
        TextView movieTitle;
        TextView movieOriginalTitle;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            movieCover = (ImageView) itemView.findViewById(R.id.iv_movie_cover);
            movieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            movieOriginalTitle = (TextView) itemView.findViewById(R.id.tv_movie_original_title);
        }
    }
}
