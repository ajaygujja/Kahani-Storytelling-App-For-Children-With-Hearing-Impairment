/*      Copyright (c) 2020 ajay gujja

        Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
        associated documentation files (the "Software"), to deal in the Software without restriction,
        including without limitation the rights to use, copy, modify, merge, publish, distribute,
        sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
        subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
        EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
        DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
        ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

*/

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
import com.gujja.ajay.fourthver.stories.Black_Sheep;
import com.gujja.ajay.fourthver.stories.HungryFox;
import com.gujja.ajay.fourthver.stories.Wise_Owl;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.PagerAdapter;
import pl.droidsonroids.gif.GifImageView;

public class Mid_Age_Adapter extends PagerAdapter {

    private int[] gif_slide = {
            R.raw.hungry_fox,
            R.raw.black_sheep,
            R.raw.wise_old_owl
    };

    //List of Images
    private int[] ist_images = {
            R.drawable.hungry_fox_logo,
            R.drawable.sheep_logo,
            R.drawable.owl_logo
    };
    // List of Titles
    private String[] ist_title = {
            "Hungry Fox",
            "Black Sheep",
            "Wise Old Owl"
    };
    // List Of Description
    private String[] ist_desc = {
            "A hungry fox who got caught in a tree hole and was beaten up by a woodcutter for stealing his lunch.",
            "A black sheep who was patient and his patience was rewarded by selling all the wool.",
            "A wise old owl who did more listening than talking and gained more wisdom as it did so."
    };

    //List of Background Color
    private int[] ist_back = {
            R.drawable.greedy_lion_gradient,
            R.drawable.black_sheep_gradient,
            R.drawable.owl_gradient
    };

    private Context context;
    public Mid_Age_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return ist_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  (view == object);
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
                i = new Intent(context, HungryFox.class);
                context.startActivity(i);
            }
            if (position == 1) {
                i = new Intent(context, Black_Sheep.class);
                context.startActivity(i);
            }
            if (position == 2) {
                i = new Intent(context, Wise_Owl.class);
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