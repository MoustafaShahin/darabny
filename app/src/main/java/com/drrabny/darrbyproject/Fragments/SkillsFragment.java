package com.drrabny.darrbyproject.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.SkillsAdapter;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.Skill;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillsFragment extends Fragment {
    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SkillsAdapter adapter;
    private ApiInterface apiInterface;
    private List<Skill> skills;
    PrefConfig prefConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_skills, container, false);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        final String token=prefConfig.readToken();
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView_skills);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Log.e("yessssspppp",token);

        Call<AccountDetail> call=apiInterface.EXPERIENCE_CALL("Bearer "+token);

        //setup progress bar before call
        final ProgressDialog progressDialog =new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Page is Loading.......");
        progressDialog.show();

        call.enqueue(new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(getActivity(), response.body().getSkills().size()+"", Toast.LENGTH_SHORT).show();
                //To handel adapter
                skills= response.body().getSkills();
                adapter=new SkillsAdapter(skills,context);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                Log.e("noo","");

            }
        });


        Button button=v.findViewById(R.id.addskill_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSkillFragment add=new AddSkillFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,add,add.getTag())
                        .commit();
            }
        });
        return v;
    }


}
