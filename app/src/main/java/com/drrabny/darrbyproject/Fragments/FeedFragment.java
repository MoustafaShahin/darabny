package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.RecyRecommendedCompanyAdapter;
import com.drrabny.darrbyproject.adapters.RecyRecommendedTrainingPostseAdapter;
import com.drrabny.darrbyproject.adapters.RecyTrainingPostseAdapter;
import com.drrabny.darrbyproject.pojoClasses.$recmondedcomp;
import com.drrabny.darrbyproject.pojoClasses.$recommendedTrainingPost;
import com.drrabny.darrbyproject.pojoClasses.Feed;
import com.drrabny.darrbyproject.pojoClasses.TrainingPost;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedFragment extends Fragment {
    Context context;
    PrefConfig prefConfig;
    ApiInterface apiInterface;
    String token;
    TextView posts,company,recommended;
    Dialog progressDialog;
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    RecyclerView recyclerView,recyclerView_recommended,recyclerView_company;
    RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager_recommended,layoutManagerCompany;
    RecyTrainingPostseAdapter recyTrainingPostseAdapter;
    RecyRecommendedTrainingPostseAdapter recyRecommendedTrainingPostseAdapter;
    RecyRecommendedCompanyAdapter recyRecommendedCompanyAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        initialize(view);

        ShowWaiting();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Feed> feedCall =apiInterface.FEED_CALL("Bearer "+token);
        feedCall.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                progressDialog.dismiss();
                List<TrainingPost> trainingPosts=response.body().getTrainingPosts();
                recyTrainingPostseAdapter=new RecyTrainingPostseAdapter(context,trainingPosts);
                recyclerView.setAdapter(recyTrainingPostseAdapter);

                List<$recommendedTrainingPost> recommendedTrainingPosts =response.body().get$recommendedTrainingPosts();
                recyRecommendedTrainingPostseAdapter =new RecyRecommendedTrainingPostseAdapter(context,recommendedTrainingPosts);
                recyclerView_recommended.setAdapter(recyRecommendedTrainingPostseAdapter);

                List<$recmondedcomp> $recmondedcomps=response.body().get$recmondedcomp();
                recyRecommendedCompanyAdapter=new RecyRecommendedCompanyAdapter(context,$recmondedcomps);
                recyclerView_company.setAdapter(recyRecommendedCompanyAdapter);
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
progressDialog.dismiss();
            }
        });


        return view;
    }

    private void initialize(View view) {

        context =getActivity();
        prefConfig =new PrefConfig(context);
        token =prefConfig.readToken();

        recyclerView =view.findViewById(R.id.recyclerView_trainingPost);
        layoutManager =new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_recommended =view.findViewById(R.id.recyclerView_recommended_trainingPost);
        layoutManager_recommended =new LinearLayoutManager(context);
        recyclerView_recommended.setLayoutManager(layoutManager_recommended);

        recyclerView_company =view.findViewById(R.id.recyclerView_recommended_company);
        layoutManagerCompany =new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_company.setLayoutManager(layoutManagerCompany);

        posts =view.findViewById(R.id.txt_training_posts);
        recommended =view.findViewById(R.id.txt_recommended_posts);
        company =view.findViewById(R.id.txt_recommended_company);
    }

}
