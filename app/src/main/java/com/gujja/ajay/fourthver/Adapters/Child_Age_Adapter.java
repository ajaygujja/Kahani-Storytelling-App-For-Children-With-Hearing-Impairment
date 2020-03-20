package com.gujja.ajay.fourthver.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gujja.ajay.fourthver.R;
import com.gujja.ajay.fourthver.stories.Cat_Dog;
import com.gujja.ajay.fourthver.stories.FourthStory;
import com.gujja.ajay.fourthver.stories.Scared_Lil_Mouse;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.PagerAdapter;
import pl.droidsonroids.gif.GifImageView;

public class Child_Age_Adapter extends PagerAdapter {

    private int[] gif_slide = {
            R.drawable.hungry_fox,
            R.drawable.hungry_fox,
            R.drawable.hungry_fox
    };

    //List of Images
    private int[] ist_images = {
            R.drawable.cat_dog_logo, //
            R.drawable.mouse_logo,
            R.drawable.lion_mouse_logo
    };
    // List of Titles
    private String[] ist_title = {
            "Cat and Dog",
            "Scared Little Mouse",
            "Lion and a Mouse"
    };
    // List Of Description
    private String[] ist_desc = {
            "A hungry fox who got caught in a tree hole and was beaten up by a woodcutter for stealing his lunch.",
            "A lion who became greedy for food and at the end was left with nothing.",
            "A lion who threatened the mouse but was saved by the same mouse during crisis",
    };

    public int[] actLayout = {
            R.layout.activity_hungry_fox,
            R.layout.activity_greedy_lion,
            R.layout.activity_third_story,
            R.layout.activity_fourth_story,

    };

    //List of Background Color
    private int[] ist_back = {
            R.drawable.gradient1,
            R.drawable.grdient3,
            R.drawable.gradient4
    };
    private Context context;

    public Child_Age_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ist_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (CoordinatorLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.slide, container, false);


        CoordinatorLayout layout_slide = view.findViewById(R.id.slideLinearLayout);
        ImageView img_slide = view.findViewById(R.id.slideImg);
        GifImageView gifImageView = view.findViewById(R.id.SlideGif);
        TextView txt_title = view.findViewById(R.id.TxtTitle);
        TextView txt_Desc = view.findViewById(R.id.textDesc);
        Button read_btn = view.findViewById(R.id.btnOrder);

        layout_slide.setBackgroundResource(ist_back[position]);
        gifImageView.setBackgroundResource(gif_slide[position]);
        img_slide.setImageResource(ist_images[position]);
        txt_title.setText(ist_title[position]);
        txt_Desc.setText(ist_desc[position]);

        read_btn.setOnClickListener(v -> {
            Intent i;
            if (position == 0) {
                i = new Intent(context, Cat_Dog.class);
                context.startActivity(i);
            }
            if (position == 1) {
                i = new Intent(context, Scared_Lil_Mouse.class);
                context.startActivity(i);
            }

            if (position == 2) {
                i = new Intent(context, FourthStory.class);
                context.startActivity(i);
            }

        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((CoordinatorLayout) object);
    }
}
