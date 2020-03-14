package com.gujja.ajay.fourthver.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.HomeActivity;
import com.gujja.ajay.fourthver.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import pl.droidsonroids.gif.GifImageView;

public class AgeSelectionAdapter extends PagerAdapter {

    public int[] first_gif = {

            R.drawable.jumping_girl_animation,
            R.drawable.hand_waving_animation,
            R.drawable.lady_guitarplayer
    };


    public  String[] first_agetext = {
            "1 - 3 Age",
            "4 - 8 Age",
            "9 - 12 Age"
    };


    Context context;
    LayoutInflater inflater;

    public AgeSelectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return first_agetext.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.age_slide, container, false);

        LinearLayout layoutslide = view.findViewById(R.id.Age_Linear_layout);
        GifImageView gifImageView = view.findViewById(R.id.Age_Lottie);
        TextView textView = view.findViewById(R.id.Age_Selection_Number);
        Button selectbtn = view.findViewById(R.id.Age_Selection_Number_Btn);


        gifImageView.setImageResource(first_gif[position]);
        textView.setText(first_agetext[position]);

        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (position == 0) {
                    i = new Intent(context, HomeActivity.class);
                    context.startActivity(i);
                }
                if (position == 1) {
                    i = new Intent(context, HomeActivity.class);
                    context.startActivity(i);
                }
                if (position == 2) {
                    i = new Intent(context, HomeActivity.class);
                    context.startActivity(i);
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
