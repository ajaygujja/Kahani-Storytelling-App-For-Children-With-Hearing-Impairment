package com.gujja.ajay.fourthver;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        lottieAnimationView = findViewById(R.id.splashscreen);

        startanimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent = new Intent(splashscreen.this,AgeSelection.class);
                startActivity(SplashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    private void startanimation(){
        ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress((float)animation.getAnimatedValue());
            }
        });

        if (lottieAnimationView.getProgress() == 0f){
            animator.start();
        }else {
            lottieAnimationView.setProgress(0f);
        }
    }


}
