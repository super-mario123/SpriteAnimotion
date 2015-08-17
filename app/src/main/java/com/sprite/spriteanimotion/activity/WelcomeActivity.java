package com.sprite.spriteanimotion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.sprite.spriteanimotion.R;
import com.sprite.spriteanimotion.utils.GUIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dings on 2015/8/15.
 */
public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.activity_welcome_view) KenBurnsView mWelcomeView;
    @Bind(R.id.activity_tv_pass) TextView mTvPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        GUIUtils.makeTheStatusbarTranslucent(this);
        ButterKnife.bind(this);
        welcomeView();
        pass();
    }

    public void pass(){
        mTvPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void welcomeView(){
        mWelcomeView.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWelcomeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWelcomeView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
