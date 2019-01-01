package com.drrabny.darrbyproject.Fragments;

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
import android.app.Fragment;
import android.widget.Button;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.languageAdapter;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.Language;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LanguagesFragment extends Fragment {

    Context context;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private languageAdapter languageAdapter;
    private ApiInterface apiInterface;
    private List<Language> languages;
    PrefConfig prefConfig;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_languages, container, false);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        final String token=prefConfig.readToken();
        recyclerView=(RecyclerView)v.findViewById(R.id.recycleView_languages);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
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
                languages= response.body().getLanguages();
                languageAdapter =new languageAdapter(languages,context);
                recyclerView.setAdapter(languageAdapter);

            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Log.e("noo","");

            }
        });

        Button button=v.findViewById(R.id.addlang_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddLanguageFragment add=new AddLanguageFragment();
                FragmentManager manager=getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,add,add.getTag())
                        .commit();
            }
        });
        return v;
    }


}
