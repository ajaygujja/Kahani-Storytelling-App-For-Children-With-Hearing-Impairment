package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.gujja.ajay.fourthver.Adapters.Mid_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mid_Age_Group extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.Mid_Age_ViewPager)
    ViewPager MidAge_ViewPager;
    @BindView(R.id.Mid_Age_layout_dots)
    DotsIndicator MidAge_Layout_Dots;
    @BindView(R.id.Mid_Arrow)
    ImageButton MidArrow;

    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid__age__group);
        ButterKnife.bind(this);

        Mid_Age_Adapter mid_age_adapter = new Mid_Age_Adapter(this);
        MidAge_ViewPager.setAdapter(mid_age_adapter);

        MidAge_Layout_Dots.setViewPager(MidAge_ViewPager);

        MidAge_ViewPager.addOnPageChangeListener(this);
        MidArrow.setOnClickListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (position == 2) {
            MidArrow.setEnabled(false);
            MidArrow.setVisibility(View.INVISIBLE);
        } else {
            MidArrow.setEnabled(true);
            MidArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        MidAge_ViewPager.setCurrentItem(currentPage + 1);
    }
}
