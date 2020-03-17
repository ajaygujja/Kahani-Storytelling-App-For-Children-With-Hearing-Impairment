package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.Adapters.Mid_Age_Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mid_Age_Group extends AppCompatActivity {

    @BindView(R.id.Mid_Age_ViewPager)
    ViewPager MidAge_ViewPager;
    @BindView(R.id.Mid_Age_layout_dots)
    LinearLayout MidAge_Layout_Dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid__age__group);
        ButterKnife.bind(this);

        Mid_Age_Adapter mid_age_adapter = new Mid_Age_Adapter(this);
        MidAge_ViewPager.setAdapter(mid_age_adapter);

        addDotIndicator(0);
        MidAge_ViewPager.addOnPageChangeListener(viewlistner);
    }

    public void addDotIndicator(int position) {
        TextView[] mDots = new TextView[3];
        MidAge_Layout_Dots.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            MidAge_Layout_Dots.addView(mDots[i]);
        }

        mDots[position].setTextColor(getResources().getColor(R.color.white));
    }

    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



}
