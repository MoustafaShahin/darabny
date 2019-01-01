package com.drrabny.darrbyproject.Fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.ProjectAdapter;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.Project;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjectsFragment extends Fragment {

    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ProjectAdapter adapter;
    private ApiInterface apiInterface;
    private List<Project> projects;
    PrefConfig prefConfig;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_projects, container, false);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        final String token=prefConfig.readToken();
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView_projects);
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

                //To handel adapter
                projects= response.body().getProjects();
                adapter=new ProjectAdapter(context,projects);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                Log.e("noo","");

            }
        });
        return v;

    }


}
