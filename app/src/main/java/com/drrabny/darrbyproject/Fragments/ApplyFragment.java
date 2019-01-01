package com.drrabny.darrbyproject.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.Exam;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyFragment extends Fragment {

    TextView companyName, decrption, fromTo, examDuration, numQusrtion, minDwegree;
    Button startExam;
    Context context;
    private ApiInterface apiInterface;
    PrefConfig prefConfig;
    String token;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_apply, container, false);

       /* Bundle bundle=getArguments();
        if (bundle != null){
            String p=bundle.getString("post");
          // TrainingPost post=new JsonObject(p);
        }*/


        initview();
        onclick();
        callExam();
        return v;
    }

    private void initview() {
        context = getActivity();
        prefConfig = new PrefConfig(context);
        token = prefConfig.readToken();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        companyName = v.findViewById(R.id.txt_apply_company_name);
        decrption = v.findViewById(R.id.txt_apply_descreption);
        fromTo = v.findViewById(R.id.txt_apply_duration);
        examDuration = v.findViewById(R.id.txt_apply_exam_duration);
        numQusrtion = v.findViewById(R.id.txt_apply_num_question);
        minDwegree = v.findViewById(R.id.txt_apply_min_degree);
        startExam = v.findViewById(R.id.btn_apply_start_exam);
    }

    private void onclick() {
        startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExamFragment examFragment = new ExamFragment();
                FragmentManager manager = ((Activity) context).getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame, examFragment, examFragment.getTag())
                        .commit();
            }
        });
    }

    private void callExam() {
        Call<Exam> call = apiInterface.EXAM_CALL("Bearer " + token);
        call.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                if (response.code() == 200) {
                    companyName.setText(response.body().getExameQuestions().getName());
                    decrption.setText(response.body().getExameQuestions().getDescription());
                    fromTo.setText(response.body().getExameQuestions().getStart() + " TO " + response.body().getExameQuestions().getEnd());
                    examDuration.setText(response.body().getExameQuestions().getTime() + " Min");
                    minDwegree.setText(response.body().getExameQuestions().getPassDegree().toString());
                    numQusrtion.setText(response.body().getExameQuestions().getQuestionNo().toString());
                }

            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {

            }
        });

    }
}
