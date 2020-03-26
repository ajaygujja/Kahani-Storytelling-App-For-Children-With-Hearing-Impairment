package com.gujja.ajay.fourthver;

import android.os.Bundle;

import com.gujja.ajay.fourthver.Adapters.Mid_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mid_Age_Group extends AppCompatActivity {

    @BindView(R.id.Mid_Age_ViewPager)
    ViewPager MidAge_ViewPager;
    @BindView(R.id.Mid_Age_layout_dots)
    DotsIndicator MidAge_Layout_Dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid__age__group);
        ButterKnife.bind(this);

        Mid_Age_Adapter mid_age_adapter = new Mid_Age_Adapter(this);
        MidAge_ViewPager.setAdapter(mid_age_adapter);

        MidAge_Layout_Dots.setViewPager(MidAge_ViewPager);
    }




}
