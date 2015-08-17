package com.sprite.spriteanimotion.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by dings on 2015/8/10.
 */
public class ObservableScrollView extends ScrollView {

    private ScrollViewListener mViewListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewListener(ScrollViewListener scrollViewListener){
        mViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mViewListener != null){
            mViewListener.onScrollChanged(this,x,y,oldx,oldy);
        }
    }
}
