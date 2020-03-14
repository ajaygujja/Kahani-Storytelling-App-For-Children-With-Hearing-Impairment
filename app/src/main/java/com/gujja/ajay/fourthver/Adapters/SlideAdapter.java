package com.gujja.ajay.fourthver.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujja.ajay.fourthver.R;
import com.gujja.ajay.fourthver.stories.FourthStory;
import com.gujja.ajay.fourthver.stories.GreedyLion;
import com.gujja.ajay.fourthver.stories.HungryFox;
import com.gujja.ajay.fourthver.stories.ThirdStory;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {

    //List of Images
    public int[] ist_images = {
            R.drawable.hungry_fox_logo,
            R.drawable.lion_logo,
            R.drawable.donkey_logo,
            R.drawable.image_4,
    };
    // List of Titles
    public String[] ist_title = {
            "Hungry Fox",
            "Greedy Lion",
            "Foolish Donkey",
            "Lion and a Mouse "
    };
    // List Of Description
    public String[] ist_desc = {
            "A hungry fox who got caught in a tree hole and was beaten up by a woodcutter for stealing his lunch.",
            "A lion who became greedy for food and at the end was left with nothing.",
            "A donkey who tried to reduce his load by playing tricks and at the end was taught a lesson by his owner while increasing is own suffering.",
            "A lion who threatened the mouse but was saved by the same mouse during crisis",
    };

    public int[] actLayout = {
            R.layout.activity_hungry_fox,
            R.layout.activity_greedy_lion,
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
                if (position == 0) {
                    i = new Intent(context, HungryFox.class);
                    context.startActivity(i);
                }
                if (position == 1) {
                    i = new Intent(context, GreedyLion.class);
                    context.startActivity(i);
                }
                if (position == 2) {
                    i = new Intent(context, ThirdStory.class);
                    context.startActivity(i);
                }
                if (position == 3) {
                    i = new Intent(context, FourthStory.class);
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
