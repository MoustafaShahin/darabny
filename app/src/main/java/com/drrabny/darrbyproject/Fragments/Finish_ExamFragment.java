package com.drrabny.darrbyproject.Fragments;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.drrabny.darrbyproject.R;


public class Finish_ExamFragment extends android.app.Fragment {

    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_finish__exam, container, false);
        btn=v.findViewById(R.id.exam_back_to_homePage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedFragment feed=new FeedFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,feed,feed.getTag())
                        .commit();
            }
        });
        return v;
    }
}
