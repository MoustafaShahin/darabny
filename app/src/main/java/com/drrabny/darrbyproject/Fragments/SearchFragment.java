package com.drrabny.darrbyproject.Fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.SearchCompanyAdapter;
import com.drrabny.darrbyproject.adapters.SearchStudentAdapter;
import com.drrabny.darrbyproject.adapters.SearchTrainingAdapter;
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.ResponseSearch;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    private View view;
    private PrefConfig prefConfig;
    private Button searchbtn;
    private ApiInterface apiInterface;
    private Button btnT,btnC,btnS;
    private RecyclerView company,student,faculty,training;
    private SearchStudentAdapter searchStudentAdapter;
    private SearchCompanyAdapter searchCompanyAdapter;
    private SearchTrainingAdapter searchTrainingAdapter;
    private EditText SearchTxt;
    private String token;
    int flag = -1;
    Dialog progressDialog;
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_search, container, false);
        initView();
        onclick();
        return view;
    }
    private void initView(){
        prefConfig = new PrefConfig(getActivity());
        apiInterface =  ApiClient.getApiClient().create(ApiInterface.class);
        SearchTxt = view.findViewById(R.id.searchq);
        student = view.findViewById(R.id.studentRv);
        company =view.findViewById(R.id.companyRv);
        training =view.findViewById(R.id.trainingRv);
        searchbtn = view.findViewById(R.id.searchbtn);
        token = prefConfig.readToken();
        btnC = view.findViewById(R.id.compantbtn);
        btnS = view.findViewById(R.id.studentbtn);
        btnT =view.findViewById(R.id.trainingbtn);

            }
        private void onclick(){
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s =SearchTxt.getText().toString();

                if(TextUtils.isEmpty(s)){

                }
                else {
                    search(s,flag);
                    ShowWaiting();
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
               if(flag==2){
                   flag =-1;
                   btnC.setBackgroundResource(R.color.white);
                   btnC.setTextColor(R.color.Darabnii_accent);
                   btnS.setBackgroundResource(R.color.white);
                   btnT.setBackgroundResource(R.color.white);
               }else{
                   flag = 2;
                   btnC.setBackgroundResource(R.color.blueM);
                   btnC.setTextColor(R.color.white);
                   btnS.setBackgroundResource(R.color.white);
                   btnT.setBackgroundResource(R.color.white);
               }
            }
        });
            btnT.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    if(flag==4){
                        flag =-1;
                        btnT.setBackgroundResource(R.color.white);
                        btnT.setTextColor(R.color.Darabnii_accent);
                        btnS.setBackgroundResource(R.color.white);
                        btnC.setBackgroundResource(R.color.white);
                    }else{
                        flag = 4;
                        btnT.setBackgroundResource(R.color.blueM);
                        btnT.setTextColor(R.color.Darabnii_accent);
                        btnS.setBackgroundResource(R.color.white);
                        btnC.setBackgroundResource(R.color.white);
                    }
                }
            });
            btnS.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View view) {
                    if(flag==2){
                        flag =-1;
                        btnS.setBackgroundResource(R.color.white);
                        btnS.setTextColor(R.color.Darabnii_accent);
                        btnC.setBackgroundResource(R.color.white);
                        btnT.setBackgroundResource(R.color.white);
                    }else{
                        flag = 2;
                        btnS.setBackgroundResource(R.color.blueM);
                        btnS.setTextColor(R.color.Darabnii_accent);
                        btnC.setBackgroundResource(R.color.white);
                        btnT.setBackgroundResource(R.color.white);
                    }
                }
            });
        }
            private void setStudentRv(){
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                student.setLayoutManager(linearLayoutManager);
                student.setAdapter(searchStudentAdapter);
            }
    private void setCompanyRv(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        company.setLayoutManager(linearLayoutManager);
        company.setAdapter(searchCompanyAdapter);
    }
    private void setTrainingrv(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        training.setLayoutManager(linearLayoutManager);
        training.setAdapter(searchTrainingAdapter);
    }
    private void search(String x,int f){
       if(f==-1){
           Call<ResponseSearch> responseSearchCall = apiInterface.Search("Bearer "+token,x);
           responseSearchCall.enqueue(new Callback<ResponseSearch>() {
               @Override
               public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {
                   if(response.isSuccessful())
                   {
                       progressDialog.dismiss();
                       Toast.makeText(getActivity(),response.body().getResult().getStudent().size()+"" , Toast.LENGTH_SHORT).show();
                       searchStudentAdapter = new SearchStudentAdapter(response.body().getResult().getStudent(),getActivity());
                       setStudentRv();
                       searchCompanyAdapter = new SearchCompanyAdapter(response.body().getResult().getCompany(),getActivity());
                       setCompanyRv();
                       searchTrainingAdapter = new SearchTrainingAdapter(response.body().getResult().getTraningPosts(),getActivity());
                       setTrainingrv();
                   }
                   else{
                       progressDialog.dismiss();
                       Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<ResponseSearch> call, Throwable t) {
                   Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
               }
           });
       }else{
           Call<ResponseSearch> responseSearchCall2 = apiInterface.Search2("Bearer "+token,x,f);
           responseSearchCall2.enqueue(new Callback<ResponseSearch>() {
               @Override
               public void onResponse(Call<ResponseSearch> call, Response<ResponseSearch> response) {
                   if(response.isSuccessful())
                   {
                       progressDialog.dismiss();
                       Toast.makeText(getActivity(),response.body().getResult().getStudent().size()+"" , Toast.LENGTH_SHORT).show();
                       searchStudentAdapter = new SearchStudentAdapter(response.body().getResult().getStudent(),getActivity());
                       setStudentRv();
                       searchCompanyAdapter = new SearchCompanyAdapter(response.body().getResult().getCompany(),getActivity());
                       setCompanyRv();
                       searchTrainingAdapter = new SearchTrainingAdapter(response.body().getResult().getTraningPosts(),getActivity());
                       setTrainingrv();
                   }
                   else{
                       Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<ResponseSearch> call, Throwable t) {
                   progressDialog.dismiss();
                   Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
               }
           });
       }
    }

}
