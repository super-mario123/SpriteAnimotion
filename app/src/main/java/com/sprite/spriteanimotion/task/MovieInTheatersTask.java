package com.sprite.spriteanimotion.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sprite.spriteanimotion.app.SApplication;
import com.sprite.spriteanimotion.model.TopMovieList;
import com.sprite.spriteanimotion.utils.Constant;
import com.sprite.spriteanimotion.utils.ParamsUtil;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dings on 2015/8/10.
 */
public class MovieInTheatersTask extends AsyncTask<Void ,Void,TopMovieList> {

    private Context mContext;
    private SApplication mApplication;
    private TopMovieList mTopMovieList;
    private GetDataFinishListener mDataFinishListener;

    public MovieInTheatersTask(Context context){
        mContext = context;
        mApplication = (SApplication) context.getApplicationContext();
    }

    @Override
    protected TopMovieList doInBackground(Void... params) {
        Map<String,String> param = new HashMap<>();
        param.put("start","0");
        param.put("count","50");
        String url = ParamsUtil.makeUrl(Constant.IN_THEATERS,param);
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = mApplication.getOkHttpClient().newCall(request).execute();
            if (response.isSuccessful()){
                Gson gson = new Gson();
                mTopMovieList = gson.fromJson(response.body().string(),TopMovieList.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mTopMovieList;
    }

    @Override
    protected void onPostExecute(TopMovieList topMovieList) {
        super.onPostExecute(topMovieList);
        if (topMovieList != null){
            if (mDataFinishListener != null){
                mDataFinishListener.onDataFinish(topMovieList.getSubjects());
            }
        }else{
            Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
        }
    }

    public void setDataFinishListener(GetDataFinishListener getDataFinishListener){
        mDataFinishListener = getDataFinishListener;
    }

    public interface GetDataFinishListener{
        void onDataFinish(Object data);
    }
}
