package com.gujja.ajay.fourthver;

import android.os.Bundle;

import com.gujja.ajay.fourthver.Adapters.Adult_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adult_Group extends AppCompatActivity {

    @BindView(R.id.Adult_Group_ViewPager)
    ViewPager AdultGroupViewPager;
    @BindView(R.id.Adult_Group_dots)
    DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult__group);
        ButterKnife.bind(this);

        Adult_Age_Adapter adultAgeAdapter = new Adult_Age_Adapter(this);
        AdultGroupViewPager.setAdapter(adultAgeAdapter);
        dotsIndicator.setViewPager(AdultGroupViewPager);

    }

}
