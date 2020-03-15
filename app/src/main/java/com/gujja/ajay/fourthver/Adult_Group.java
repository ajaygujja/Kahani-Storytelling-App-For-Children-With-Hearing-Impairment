package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.Adapters.Adult_Age_Adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adult_Group extends AppCompatActivity {

    @BindView(R.id.Adult_Group_ViewPager)
    ViewPager AdultGroupViewPager;
    @BindView(R.id.Adult_Grouplayout_dots)
    LinearLayout AdultGrouplayoutDots;


    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult__group);
        ButterKnife.bind(this);

        Adult_Age_Adapter adultAgeAdapter = new Adult_Age_Adapter(this);
        AdultGroupViewPager.setAdapter(adultAgeAdapter);

        addDotIndicator(0);
        AdultGroupViewPager.addOnPageChangeListener(viewListner);
    }

    public void addDotIndicator(int position) {
        TextView[] mDots = new TextView[3];
        AdultGrouplayoutDots.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            AdultGrouplayoutDots.addView(mDots[i]);

        }

            mDots[position].setTextColor(getResources().getColor(R.color.white));

    }
}
