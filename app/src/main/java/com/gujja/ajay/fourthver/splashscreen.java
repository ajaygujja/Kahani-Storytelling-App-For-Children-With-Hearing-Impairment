package com.gujja.ajay.fourthver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent SplashIntent = new Intent(splashscreen.this,MainActivity.class);
                startActivity(SplashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
