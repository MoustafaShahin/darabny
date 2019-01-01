package com.drrabny.darrbyproject.Activities;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;

/**
 * Created by ahmad on 12-Feb-18.
 */

public class SliderAdapter extends PagerAdapter {

    Context  context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }
    public int[] slide_imge={
            0,
            R.drawable.ic_imfrist,
            R.drawable.ic_four,
            R.drawable.ic_imfour,
            R.drawable.ic_imfive
    };

    public  String[] slide_heading={
            "We will tack you in small tour to help you using our app",
            "Easy To Understand UI",
            "Porfitable",
            "Find Training In The Second",
            "Easy Applying"
    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==(LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageViewSlide=view.findViewById(R.id.img_slide_layout);
        TextView slideHeading=view.findViewById(R.id.txt_slide_layout);

        if (position != 0) {
            imageViewSlide.setImageResource(slide_imge[position]);
            slideHeading.setText(slide_heading[position]);
        }else {
            view = layoutInflater.inflate(R.layout.slide_layout1,container,false);
            TextView txtTitle1=view.findViewById(R.id.img_slide_layout1);
            TextView slideHeading1=view.findViewById(R.id.txt_slide_layout1);
            txtTitle1.setText("Welcome in \n Darrabny ");
            slideHeading1.setText(slide_heading[position]);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
