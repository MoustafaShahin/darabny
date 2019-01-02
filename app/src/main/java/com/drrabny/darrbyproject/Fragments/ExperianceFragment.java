package com.drrabny.darrbyproject.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.ExperienceAdapter;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.Experience;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExperianceFragment extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ExperienceAdapter adapter;
    private ApiInterface apiInterface;
    private List<Experience> experiences;
    private PrefConfig prefConfig;
    private View v;
    private String token;
    private Button button;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_experiance, container, false);
        initview();
        getExperience();
        onclick();

        return v;
    }

    private void initview() {
        context = getActivity();
        button = v.findViewById(R.id.addExper_btn);
        prefConfig = new PrefConfig(context);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView_Experience);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        token = prefConfig.readToken();
    }

    private void onclick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExperienceFragment Experience = new AddExperienceFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame, Experience, Experience.getTag())
                        .commit();
            }
        });
    }

    private void getExperience() {

        Call<AccountDetail> call = apiInterface.EXPERIENCE_CALL("Bearer " + token);
        //setup progress bar before call
        setprogress();
        call.enqueue(new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                if (response.isSuccessful()) {
                    //To handel adapter
                    experiences = response.body().getExperiences();
                    adapter = new ExperienceAdapter(experiences, context);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });

    }

    private void setprogress() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Page is Loading.......");
        progressDialog.show();
    }
}
