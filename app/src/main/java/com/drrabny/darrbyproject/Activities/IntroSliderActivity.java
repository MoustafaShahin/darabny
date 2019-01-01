package com.drrabny.darrbyproject.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.drrabny.darrbyproject.R;

public class IntroSliderActivity extends AppCompatActivity {

    ViewPager mSliderViewPager;
    LinearLayout mDotsLayout;
    SliderAdapter sliderAdapter;
    int  numPage;
    ImageView hasDots;
    private int numCurrentPage;
    ImageButton btnNext,btnBack;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        mSliderViewPager=findViewById(R.id.slideViewPager);
        getSupportActionBar().hide();
        mDotsLayout=findViewById(R.id.dots);
        btnNext=findViewById(R.id.btn_next);
        btnBack=findViewById(R.id.btn_back);
        sliderAdapter=new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);

        mDotsIndactor(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numCurrentPage < numPage-1 ) {
                    mSliderViewPager.setCurrentItem(numCurrentPage + 1);
                }else {

                    startActivity(new Intent(IntroSliderActivity.this,NavigationActivity.class));
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setCurrentItem(numCurrentPage -1);
            }
        });
    }

    public void mDotsIndactor(int postion) {
        numPage = 5;

        mDotsLayout.removeAllViews();
        hasDots = new ImageView(this);

        switch (postion) {
            case 0:
                hasDots.setImageDrawable(getResources().getDrawable(R.drawable.steps1));
                break;
            case 1:
                hasDots.setImageDrawable(getResources().getDrawable(R.drawable.steps2));
                break;
            case 2:
                hasDots.setImageDrawable(getResources().getDrawable(R.drawable.steps3));
                break;
            case 3:
                hasDots.setImageDrawable(getResources().getDrawable(R.drawable.steps4));
                break;
            case 4:
                hasDots.setImageDrawable(getResources().getDrawable(R.drawable.steps5));
                break;
        }
        mDotsLayout.addView(hasDots);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mDotsIndactor(position);
            numCurrentPage=position;

            if (position == 0){
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);
                btnBack.setImageDrawable(getResources().getDrawable(R.drawable.backnotenable));

            }else if (position == numPage-1){
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setImageDrawable(getResources().getDrawable(R.drawable.backimg));
                btnNext.setImageDrawable(getResources().getDrawable(R.drawable.finish));

            }else {
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setImageDrawable(getResources().getDrawable(R.drawable.backimg));
                btnNext.setImageDrawable(getResources().getDrawable(R.drawable.nextimg));

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
