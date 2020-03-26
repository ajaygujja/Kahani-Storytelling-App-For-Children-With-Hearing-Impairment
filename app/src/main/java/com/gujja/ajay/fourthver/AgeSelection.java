package com.gujja.ajay.fourthver;

import android.os.Bundle;

import com.gujja.ajay.fourthver.Adapters.AgeSelectionAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AgeSelection extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_selection);

        ViewPager age_selection_viewPager = findViewById(R.id.Age_selection_ViewPager);
        DotsIndicator dotsIndicator = findViewById(R.id.Age_selection_layout_dots);

        AgeSelectionAdapter age_selection_adapter = new AgeSelectionAdapter(this);
        age_selection_viewPager.setAdapter(age_selection_adapter);
        dotsIndicator.setViewPager(age_selection_viewPager);

    }

    }



