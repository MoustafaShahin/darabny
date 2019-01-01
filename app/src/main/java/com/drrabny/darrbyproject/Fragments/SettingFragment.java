package com.drrabny.darrbyproject.Fragments;


import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.drrabny.darrbyproject.R;


public class SettingFragment extends android.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_setting, container, false);
        Button button=v.findViewById(R.id.Accountbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountFragment accountFragment=new AccountFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,accountFragment,accountFragment.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ////////
        Button button2=v.findViewById(R.id.personalInfo_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Personal_InformationFragment pi=new Personal_InformationFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,pi,pi.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ////////////////
        Button button10=v.findViewById(R.id.updatePass_btn);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdatePasswordFragment Fragment=new UpdatePasswordFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,Fragment,Fragment.getTag()).addToBackStack(null)
                        .commit();
            }
        });

        Button button3=v.findViewById(R.id.language_btn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguagesFragment languages=new LanguagesFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,languages,languages.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ////////////////
        Button button4=v.findViewById(R.id.skills_btn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkillsFragment skill=new SkillsFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,skill,skill.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ///////
        Button button5=v.findViewById(R.id.experiance_btn);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExperianceFragment Experience=new ExperianceFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,Experience,Experience.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ///////
        Button button6=v.findViewById(R.id.projects_btn);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectsFragment projects=new ProjectsFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,projects,projects.getTag()).addToBackStack(null)
                        .commit();
            }
        });
        ////////
        return v;
    }

}
