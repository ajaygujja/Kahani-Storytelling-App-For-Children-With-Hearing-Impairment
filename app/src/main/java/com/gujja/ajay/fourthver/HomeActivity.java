package com.gujja.ajay.fourthver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    CardView wolfcard, story2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wolfcard = findViewById(R.id.WolfStory);
        story2 = findViewById(R.id.Story2);


        wolfcard.setOnClickListener(this);
        story2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.WolfStory:i = new Intent(this, HungryFox.class);startActivity(i);break;
            case R.id.Story2:i = new Intent(this,SecondStory.class);startActivity(i);break;
            default:break;
        }
    }
}
