package com.drrabny.darrbyproject.Fragments;

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
import com.drrabny.darrbyproject.pojoClasses.Question;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExamFragment extends Fragment {

    Context context;
    private ApiInterface apiInterface;
    PrefConfig prefConfig;
    String token;
    public List<Question> questions;
    int i;
    View view;
    TextView numQustion,currentQuestion,duration,question;
    Button next,back,ans1,ans2,ans3,ans4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_exam, container, false);
         initview();
        Call<Exam> call=apiInterface.EXAM_CALL("Bearer "+token);
        call.enqueue(new Callback<Exam>() {
            @Override
            public void onResponse(Call<Exam> call, Response<Exam> response) {
                if(response.code() ==200) {
                    numQustion.setText(response.body().getExameQuestions().getQuestionNo().toString());
                    currentQuestion.setText(i+1+"");
                    duration.setText(response.body().getExameQuestions().getTime()+"Mins");
                    questions=response.body().getExameQuestions().getQuestions();
                     i=0;
                    question.setText(questions.get(0).getQuestion());
                    ans1.setText(questions.get(i).getAnswers().get(0).getAnswer());
                    ans2.setText(questions.get(i).getAnswers().get(1).getAnswer());
                    ans3.setText(questions.get(i).getAnswers().get(2).getAnswer());
                    ans3.setText(questions.get(i).getAnswers().get(3).getAnswer());
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         calcutate(view);
                        }
                    });
                    ans1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            calcutate(view);
                        }
                    });
                    ans2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            calcutate(view);

                        }
                    });
                    ans3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            calcutate(view);
                        }
                    });
                    ans4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            calcutate(view);
                        }
                    });
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (i==0){
                                back.setClickable(false);
                            }else {
                                i--;
                                question.setText(questions.get(i).getQuestion());
                                ans1.setText(questions.get(i).getAnswers().get(0).getAnswer());
                                ans2.setText(questions.get(i).getAnswers().get(1).getAnswer());
                                ans3.setText(questions.get(i).getAnswers().get(2).getAnswer());
                                ans3.setText(questions.get(i).getAnswers().get(3).getAnswer());
                                currentQuestion.setText(i+1+"");
                            }
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<Exam> call, Throwable t) {

            }
        });


        return view;
    }
    private void initview(){
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        token=prefConfig.readToken();
        currentQuestion =view.findViewById(R.id.exam_ques_num_txt);
        numQustion =view.findViewById(R.id.exam_total_ques_num_txt);
        duration =view.findViewById(R.id.exam_time_txt);
        question=view.findViewById(R.id.exam_question_txt);

        back=view.findViewById(R.id.exam_previous_btn);
        next=view.findViewById(R.id.exam_next_btn);
        ans1=view.findViewById(R.id.exam_first_answer_txt);
        ans2=view.findViewById(R.id.exam_second_answer_txt);
        ans3=view.findViewById(R.id.exam_third_answer_txt);
        ans4=view.findViewById(R.id.exam_four_answer_txt);

    }
    private void calcutate(View view) {
        if (i==questions.size()-1){
            Finish_ExamFragment finsh=new Finish_ExamFragment();
            FragmentManager manager=getFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.content_frame,finsh,finsh.getTag())
                    .commit();
        }else {
            i++;
            question.setText(questions.get(i).getQuestion());
            ans1.setText(questions.get(i).getAnswers().get(0).getAnswer());
            ans2.setText(questions.get(i).getAnswers().get(1).getAnswer());
            ans3.setText(questions.get(i).getAnswers().get(2).getAnswer());
            ans3.setText(questions.get(i).getAnswers().get(3).getAnswer());
            currentQuestion.setText(i+1+"");
        }
    }

    void show(List<Question> questions){
        for (int i=0;i<questions.size();i++){

            question.setText(questions.get(i).getQuestion());
        }

    }

}
