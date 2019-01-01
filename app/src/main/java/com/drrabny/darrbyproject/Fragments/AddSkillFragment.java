package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.SpinAdapter;
import com.drrabny.darrbyproject.pojoClasses.ResponseFollow;
import com.drrabny.darrbyproject.pojoClasses.ResponseSkills.ResponseSkills;
import com.drrabny.darrbyproject.pojoClasses.ResponseSkills.Skill;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSkillFragment extends Fragment {
    ApiInterface apiInterface;
    PrefConfig prefConfig;
    String token;
    View view;
    Context context;
    int id=-1;
    SpinAdapter spinerAdapter;
    Spinner txt;
    Dialog progressDialog;
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    Button add;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_skill, container, false);
        ShowWaiting();
        initview();
        getSkills();
        return view;
    }
    private void initview(){
        context=getActivity();
        prefConfig=new PrefConfig(context);
        token=prefConfig.readToken();
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        txt = view.findViewById(R.id.skilltxt);
        add= view.findViewById(R.id.savebtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id !=-1){
                    add();
                }
            }
        });
        txt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Skill skill =(Skill) adapterView.getItemAtPosition(i);
                id = skill.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void getSkills(){
        Call<ResponseSkills>responseSkillsCall=apiInterface.RESPONSE_SKILLS_CALL("Bearer "+token);
        responseSkillsCall.enqueue(new Callback<ResponseSkills>() {
            @Override
            public void onResponse(Call<ResponseSkills> call, Response<ResponseSkills> response) {
               progressDialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("true")){
                    spinerAdapter = new SpinAdapter(context,response.body().getSkills());
                    txt.setAdapter(spinerAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseSkills> call, Throwable t) {
progressDialog.dismiss();
            }
        });
    }
    private void add(){
        Call<ResponseFollow>addSkill = apiInterface.ADD_SKILL_CALL("Bearer " +token,id);
        addSkill.enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("true")){
                        Toast.makeText(context, response.body().getSuccess().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {

            }
        });
    }


}
