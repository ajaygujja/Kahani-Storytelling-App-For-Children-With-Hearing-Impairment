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

package com.gujja.ajay.fourthver;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.gujja.ajay.fourthver.Adapters.Child_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Child_Group extends AppCompatActivity implements androidx.viewpager.widget.ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.ViewPager)
    androidx.viewpager.widget.ViewPager ViewPager;
    @BindView(R.id.layout_dots)
    DotsIndicator dotsIndicator;
    @BindView(R.id.Child_arrow)
    ImageButton ChildArrow;

    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_group);
        ButterKnife.bind(this);

        Child_Age_Adapter my_adapter = new Child_Age_Adapter(this);
        ViewPager.setAdapter(my_adapter);
        dotsIndicator.setViewPager(ViewPager);

        ViewPager.addOnPageChangeListener(this);
        ChildArrow.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (position == 2) {
            ChildArrow.setEnabled(false);
            ChildArrow.setVisibility(View.INVISIBLE);
        } else {
            ChildArrow.setEnabled(true);
            ChildArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View view) {
        ViewPager.setCurrentItem(currentPage + 1);
    }
}
