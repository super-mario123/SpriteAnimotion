package com.sprite.spriteanimotion.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sprite.spriteanimotion.app.SApplication;
import com.sprite.spriteanimotion.model.Movie;
import com.sprite.spriteanimotion.utils.Constant;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by dings on 2015/8/9.
 */
public class MovieTask extends AsyncTask<String,Void,Movie> {

    private static final String TAG = "MovieTask";
    private Context mContext;
    private SApplication mApplication;
    private Movie movie;

    private GetDataFinishListener mDataFinishListener;

    public MovieTask(Context context){
        mContext = context;
        mApplication = (SApplication) context.getApplicationContext();
    }


    @Override
    protected Movie doInBackground(String... params) {

        Request request = new Request.Builder().url(Constant.SUBJECT+params[0]).build();
        try {
            Response response = mApplication.getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful()){
                Gson gson = new Gson();
                movie = gson.fromJson(response.body().string(),Movie.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
        if (movie != null){
            if (mDataFinishListener != null){
                mDataFinishListener.onDataFinish(movie);
            }
        }else{
            Toast.makeText(mContext,"网络异常",Toast.LENGTH_SHORT).show();
        }
    }

    public void setDataFinishListener(GetDataFinishListener getDataFinishListener){
        mDataFinishListener = getDataFinishListener;
    }

    public interface GetDataFinishListener{
        void onDataFinish(Object data);
    }
}
