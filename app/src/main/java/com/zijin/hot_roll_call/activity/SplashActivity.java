package com.zijin.hot_roll_call.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.zijin.hot_roll_call.R;

public class SplashActivity extends Activity {
    private ImageView iv_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        iv_splash = (ImageView) findViewById(R.id.iv_splash);
        //执行一个启动动画效果
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(2000);//设置动画时间
        iv_splash.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iv_splash.setBackgroundResource(R.drawable.splash);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                skip();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            private void skip() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
