package com.gujja.ajay.fourthver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {

    //List of Images
    public int[] ist_images = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
    };
    // List of Titles
    public String[] ist_title = {
            "Hungry Fox",
            "Greedy Lion",
            "Foolish Donkey",
            "Story 4"
    };
    // List Of Description
    public String[] ist_desc = {
            "The hungry fox who got caught in the tree trunk" +
                    " while WoodCutter was Working",
            "Donkey and A Seller." +
                    "Donkey gets lucky everytime." +
                    "But this time luck Ran out this Time",
            "Description 3",
            "Description 4",
    };

    public int[] actLayout = {
            R.layout.activity_hungry_fox,
            R.layout.activity_second_story,
            R.layout.activity_third_story,
            R.layout.activity_fourth_story,

    };

    //List of Background Color
    public int[] ist_back = {
            R.drawable.gradient1,
            R.drawable.gradient2,
            R.drawable.grdient3,
            R.drawable.gradient4
    };
    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ist_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);

        LinearLayout layoutslide = view.findViewById(R.id.slideLinearLayout);
        ImageView imgslide = view.findViewById(R.id.slideImg);
        TextView txttitle = view.findViewById(R.id.TxtTitle);
        TextView txtDesc = view.findViewById(R.id.textDesc);
        Button readbtn = view.findViewById(R.id.btnOrder);

        layoutslide.setBackgroundResource(ist_back[position]);
        imgslide.setImageResource(ist_images[position]);
        txttitle.setText(ist_title[position]);
        txtDesc.setText(ist_desc[position]);

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (position == 0){
                    i = new Intent(context,HungryFox.class); context.startActivity(i);
                }if (position == 1){
                    i = new Intent(context,SecondStory.class); context.startActivity(i);
                }if (position == 2){
                    i = new Intent(context,ThirdStory.class); context.startActivity(i);
                }if (position == 3){
                    i = new Intent(context,FourthStory.class); context.startActivity(i);
                }



                //Intent intent = new Intent(context,actLayout[position].class);
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
