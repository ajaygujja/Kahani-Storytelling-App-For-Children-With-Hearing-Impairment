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

import com.gujja.ajay.fourthver.Adapters.Adult_Age_Adapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adult_Group extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @BindView(R.id.Adult_Group_ViewPager)
    ViewPager AdultGroupViewPager;
    @BindView(R.id.Adult_Group_dots)
    DotsIndicator dotsIndicator;
    @BindView(R.id.Adult_arrow)
    ImageButton Adult_Arrow;

    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult__group);
        ButterKnife.bind(this);

        Adult_Age_Adapter adultAgeAdapter = new Adult_Age_Adapter(this);
        AdultGroupViewPager.setAdapter(adultAgeAdapter);
        dotsIndicator.setViewPager(AdultGroupViewPager);

        AdultGroupViewPager.addOnPageChangeListener(this);
        Adult_Arrow.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (position == 2) {
            Adult_Arrow.setEnabled(false);
            Adult_Arrow.setVisibility(View.INVISIBLE);
        } else {
            Adult_Arrow.setEnabled(true);
            Adult_Arrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View view) {
        AdultGroupViewPager.setCurrentItem(currentPage + 1);
    }
}
