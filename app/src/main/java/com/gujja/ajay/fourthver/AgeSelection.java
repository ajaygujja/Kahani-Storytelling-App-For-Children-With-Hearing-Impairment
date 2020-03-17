package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.Adapters.AgeSelectionAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AgeSelection extends AppCompatActivity {

    private ViewPager Age_selection_viewPager;
    private LinearLayout Age_selection_DotLayout;
    private AgeSelectionAdapter Age_selection_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_selection);

        Age_selection_viewPager = findViewById(R.id.Age_selection_ViewPager);
        Age_selection_DotLayout = findViewById(R.id.Age_selection_layout_dots);

        Age_selection_adapter = new AgeSelectionAdapter(this);
        Age_selection_viewPager.setAdapter(Age_selection_adapter);

        addDotIndicator(0);
        Age_selection_viewPager.addOnPageChangeListener(viewlistner);


    }


    public void addDotIndicator(int position) {
        TextView[] age_selection_Dots = new TextView[3];
        Age_selection_DotLayout.removeAllViews();

        for (int i = 0; i < age_selection_Dots.length; i++) {
            age_selection_Dots[i] = new TextView(this);
            age_selection_Dots[i].setText(Html.fromHtml("&#8226"));
            age_selection_Dots[i].setTextSize(35);
            age_selection_Dots[i].setTextColor(getResources().getColor(R.color.black));
            Age_selection_DotLayout.addView(age_selection_Dots[i]);
        }

        age_selection_Dots[position].setTextColor(getResources().getColor(R.color.red));
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



