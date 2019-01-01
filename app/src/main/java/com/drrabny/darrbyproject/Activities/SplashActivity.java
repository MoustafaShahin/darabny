package com.drrabny.darrbyproject.Activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progress;
    PrefConfig prefConfig;
    Boolean frist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        startAnimation();
        prefConfig = new PrefConfig(this);
        frist =false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (frist) {
                    startActivity(new Intent(SplashActivity.this, IntroSliderActivity.class));
                }else {
                    if (prefConfig.readLoginStatus()) {
                        startActivity(new Intent(SplashActivity.this, NavigationActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
                SplashActivity.this.finish();
            }
        },2000);
    }

    private void startAnimation(){
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        progressAnimator.setDuration(3000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.start();
    }

}
