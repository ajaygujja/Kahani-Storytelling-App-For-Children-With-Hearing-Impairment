package com.gujja.ajay.fourthver;

import android.os.Bundle;

import com.gujja.ajay.fourthver.Adapters.Child_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Child_Group extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_group);

        ViewPager viewPager = findViewById(R.id.ViewPager);
        DotsIndicator dotsIndicator = findViewById(R.id.layoutdots);


        Child_Age_Adapter myadapter = new Child_Age_Adapter(this);
        viewPager.setAdapter(myadapter);
        dotsIndicator.setViewPager(viewPager);

    }

}
