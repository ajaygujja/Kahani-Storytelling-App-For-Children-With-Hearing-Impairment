package com.gujja.ajay.fourthver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter myadapter;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.ViewPager);
        //mDotLayout = findViewById(R.id.Dot);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);


    }

   /* public void addDotIndicator(){
        mDots = new TextView[3];
        for (int i = 0; i < mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));
            mDotLayout.addView(mDots[i]);
        }
    }*/




    @Override
    public void onClick(View v) {
        Intent i;
       /* switch (v.getId()){
            case R.id.WolfStory:i = new Intent(this, HungryFox.class);startActivity(i);break;
            case R.id.Story2:i = new Intent(this,SecondStory.class);startActivity(i);break;
            default:break;
        }*/
    }
}
