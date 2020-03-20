package com.gujja.ajay.fourthver.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.Adult_Group;
import com.gujja.ajay.fourthver.Child_Group;
import com.gujja.ajay.fourthver.Mid_Age_Group;
import com.gujja.ajay.fourthver.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import pl.droidsonroids.gif.GifImageView;

public class AgeSelectionAdapter extends PagerAdapter {

    private int[] first_gif = {

            R.drawable.jumping_girl_animation,
            R.drawable.hand_waving_animation,
            R.drawable.lady_guitarplayer
    };


    private String[] first_agetext = {
            "1 - 3 Age",
            "4 - 8 Age",
            "9 - 12 Age"
    };


    private Context context;

    public AgeSelectionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return first_agetext.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.age_slide, container, false);

        LinearLayout layout_slide = view.findViewById(R.id.Age_Linear_layout);
        GifImageView gifImageView = view.findViewById(R.id.Age_Lottie);
        TextView textView = view.findViewById(R.id.Age_Selection_Number);
        Button select_btn = view.findViewById(R.id.Age_Selection_Number_Btn);


        gifImageView.setImageResource(first_gif[position]);
        textView.setText(first_agetext[position]);

        select_btn.setOnClickListener(v -> {
            Intent i;
            if (position == 0) {
                i = new Intent(context, Child_Group.class);
                context.startActivity(i);
            }
            if (position == 1) {
                i = new Intent(context, Mid_Age_Group.class);
                context.startActivity(i);
            }
            if (position == 2) {
                i = new Intent(context, Adult_Group.class);
                context.startActivity(i);
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
