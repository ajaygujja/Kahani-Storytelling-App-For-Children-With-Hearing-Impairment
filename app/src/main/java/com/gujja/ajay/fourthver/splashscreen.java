/*      Copyright (c) 2020 ajay gujja

        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
        associated documentation files (the "Software"), to deal in the Software without restriction,
        including without limitation the rights to use, copy, modify, merge, publish, distribute,
        sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
        subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
        EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
        DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
        ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

package com.gujja.ajay.fourthver;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);
        lottieAnimationView = findViewById(R.id.splashscreen);

        start_animation();

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent = new Intent(splashscreen.this, AgeSelection.class);
                splashscreen.this.startActivity(SplashIntent);
                splashscreen.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void start_animation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(3000);
        animator.addUpdateListener(animation -> lottieAnimationView.setProgress((float) animation.getAnimatedValue()));

        if (lottieAnimationView.getProgress() == 0f) {
            animator.start();
        } else {
            lottieAnimationView.setProgress(0f);
        }
    }

}
