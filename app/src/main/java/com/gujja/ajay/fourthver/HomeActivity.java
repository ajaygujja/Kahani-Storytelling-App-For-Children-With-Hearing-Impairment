package com.gujja.ajay.fourthver;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity  {

    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter myadapter;
    private TextView[] mDots;
    private int currentpage;
    private Button nextbtn,prevbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.ViewPager);
        mDotLayout = findViewById(R.id.layoutdots);
        nextbtn = findViewById(R.id.nextbtn);
        prevbtn = findViewById(R.id.prevbtn);

        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);

        addDotIndicator(0);
        viewPager.addOnPageChangeListener(viewlistner);

    }

    public void addDotIndicator(int position){
        mDots = new TextView[4];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));

            mDotLayout.addView(mDots[i]);

        }

        if(mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewlistner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
            currentpage = position;
            if (position == 0){
                nextbtn.setEnabled(true);
                prevbtn.setEnabled(false);
                prevbtn.setVisibility(View.INVISIBLE);

                nextbtn.setText("Next");

            }
            else if (position == mDots.length - 1){
                nextbtn.setEnabled(true);
                prevbtn.setEnabled(true);
                prevbtn.setVisibility(View.VISIBLE);

                nextbtn.setText("Finish");
            }
            else {
                nextbtn.setEnabled(true);
                prevbtn.setEnabled(true);
                prevbtn.setVisibility(View.VISIBLE);

                nextbtn.setText("Next");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
