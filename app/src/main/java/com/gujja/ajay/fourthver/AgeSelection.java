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

import com.gujja.ajay.fourthver.Adapters.AgeSelectionAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AgeSelection extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.Age_selection_ViewPager)
    ViewPager Age_ViewPager;
    @BindView(R.id.Age_selection_layout_dots)
    DotsIndicator dotsIndicator;
    @BindView(R.id.Age_selection_arrow)
    ImageButton Age_Arrow;

    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_selection);
        ButterKnife.bind(this);

        AgeSelectionAdapter age_selection_adapter = new AgeSelectionAdapter(this);
        Age_ViewPager.setAdapter(age_selection_adapter);
        dotsIndicator.setViewPager(Age_ViewPager);

        Age_ViewPager.addOnPageChangeListener(this);
        Age_Arrow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Age_ViewPager.setCurrentItem(currentPage + 1);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (position == 2) {
            Age_Arrow.setEnabled(false);
            Age_Arrow.setVisibility(View.INVISIBLE);
        } else {
            Age_Arrow.setEnabled(true);
            Age_Arrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}



