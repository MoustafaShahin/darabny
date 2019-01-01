package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.RecyExpereinceAdapter;
import com.drrabny.darrbyproject.adapters.RecyFollwingAdapter;
import com.drrabny.darrbyproject.adapters.RecySkillAdapter;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.AccountInfo;
import com.drrabny.darrbyproject.pojoClasses.Experience;
import com.drrabny.darrbyproject.pojoClasses.Following;
import com.drrabny.darrbyproject.pojoClasses.Skill;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentProfileFragment extends Fragment {

    View view;
    PrefConfig prefConfig;
    Context context;
    ApiInterface apiInterface;
    String token;
    TextView name,job,address,coverLetter,numProject,numLanguage;
    //for image profile
    ImageView profile_photo;
    //for popup following
    Button btn_follow;
    LinearLayout linearLayout;
    //contacts
    TextView linked,sky,tele,mail;
    View vlinked,vsky,vtele,vmail;
    // for Adapters
    RecyclerView recyclerViewExp,recyclerViewSkill;
    RecyExpereinceAdapter expereinceAdapter;
    RecySkillAdapter skillAdapter;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    Dialog progressDialog;
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    List<Following> followings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_student_profile, container, false);
        initialize(view);
        ShowWaiting();
        //call retrofit
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final Call<AccountDetail> accountDetailCall= apiInterface.ACCOUNT_DETAIL_CALL("Bearer "+token);
        accountDetailCall.enqueue(new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {
            progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e("success", response.code() + "");
                    //set data in view
                    onResponseSuccessful(response);
                }else {
                    Log.e("not success", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
               progressDialog.dismiss();
                Log.e("Failure","the request is fail");
            }
        });


        return view;
    }
    private void initialize(View view) {
        context =getActivity();
        prefConfig =new PrefConfig(context);
        token=prefConfig.readToken();

        //find view
        name=view.findViewById(R.id.txt_stProfile_name);
        job=view.findViewById(R.id.txt_stProfile_jobTitle);
        address=view.findViewById(R.id.txt_stProfile_address);
        coverLetter=view.findViewById(R.id.txt_stProfile_coverLetter);
        numProject=view.findViewById(R.id.txt_stProfile_num_project);
        numLanguage=view.findViewById(R.id.txt_stProfile_num_Language);
        //for profile photo
        profile_photo =view.findViewById(R.id.im_stProfile_profile);

        //expereince recy
        recyclerViewExp=view.findViewById(R.id.recyclerView_exp);
        layoutManager=new LinearLayoutManager(context);
        recyclerViewExp.setLayoutManager(layoutManager);
        recyclerViewExp.setHasFixedSize(false);
       // recyclerViewExp.setNestedScrollingEnabled(true);

        //skill recy
        recyclerViewSkill=view.findViewById(R.id.recyclerView_skill);
        layoutManager2=new LinearLayoutManager(context);
        recyclerViewSkill.setLayoutManager(layoutManager2);
        recyclerViewSkill.setHasFixedSize(true);

        //contacts
        linked=view.findViewById(R.id.txt_stProfile_linkedIn);
        sky=view.findViewById(R.id.txt_stProfile_skype);
        tele=view.findViewById(R.id.txt_stProfile_telephone);
        mail=view.findViewById(R.id.txt_stProfile_mail);

        vlinked=view.findViewById(R.id.txt_stProfile_linkedIn_v);
        vsky=view.findViewById(R.id.txt_stProfile_skype_v);
        vtele=view.findViewById(R.id.txt_stProfile_telephone_v);
        vmail=view.findViewById(R.id.txt_stProfile_mail_v);


        //for popup following
        btn_follow=view.findViewById(R.id.btn_stProfile_follow);
        linearLayout=view.findViewById(R.id.stProfile_linearLayout);
        btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                followingPopup(view);
            }
        });

    }

    private void followingPopup(View view) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout/view
        View view1 =inflater.inflate(R.layout.popup_layout,null);
        // Initialize a new instance of popup window
        final PopupWindow popupWindow=new PopupWindow(
                view1,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                false);

        popupWindow.setOutsideTouchable(false);
        btn_follow.setEnabled(false);

        if(Build.VERSION.SDK_INT>=21){
            popupWindow.setElevation(5.0f);
        }
        ImageButton closeButton = (ImageButton) view1.findViewById(R.id.im_stProfile_popup_close);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                popupWindow.dismiss();
                btn_follow.setEnabled(true);
            }
        });


        popupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);

        FrameLayout frameLayout =view1.findViewById(R.id.frame_stProfile_popup);
        frameLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                btn_follow.setEnabled(true);
            }
        });


        //follow recy
        RecyclerView recyclerViewFollow = view1.findViewById(R.id.recy_stProfile_popup_follow);
        RecyclerView.LayoutManager layoutManager3=new LinearLayoutManager(context);
        recyclerViewFollow.setLayoutManager(layoutManager3);
        recyclerViewFollow.setHasFixedSize(true);
        RecyFollwingAdapter follwingAdapter = new RecyFollwingAdapter(context, followings);
        recyclerViewFollow.setAdapter(follwingAdapter);
    }

    private void onResponseSuccessful(Response<AccountDetail> response) {

        //set profile photo
        Picasso.with(context).load(response.body().getAccountInfo().getpP()).into(profile_photo);
        //set user name and lname
        if (!(response.body().getAccountInfo().getFirstName().equals(""))) {
            name.setVisibility(View.VISIBLE);
            name.setText(response.body().getAccountInfo().getFirstName() + " " + response.body().getAccountInfo().getLastName());
        }
        //set job title
        if (!(response.body().getAccountInfo().getJobTitle().equals(""))) {
            job.setVisibility(View.VISIBLE);
            job.setText(response.body().getAccountInfo().getJobTitle());
        }
        //set address
        if (!(response.body().getAccountInfo().getUniversity().equals(""))) {
            address.setVisibility(View.VISIBLE);
            address.setText(response.body().getAccountInfo().getUniversity() + "\n" + response.body().getAccountInfo().getFaculty());
        }
        if (!(response.body().getAccountInfo().getBio().equals(""))) {
            coverLetter.setVisibility(View.VISIBLE);
            coverLetter.setText(response.body().getAccountInfo().getBio());
        }
        numProject.setText(response.body().getProjects().size()+"");
        numLanguage.setText(response.body().getLanguages().size()+"");

        //exp
        List<Experience> experiences = response.body().getExperiences();
        expereinceAdapter =new RecyExpereinceAdapter(context,experiences);
        recyclerViewExp.setAdapter(expereinceAdapter);

        //skills
        List<Skill> skills=response.body().getSkills();
        skillAdapter =new RecySkillAdapter(context,skills);
        recyclerViewSkill.setAdapter(skillAdapter);

        //follow
        followings = response.body().getFollowing();


        //contact
        Contacts(response.body().getAccountInfo());

    }

    private void Contacts(AccountInfo accountInfo) {
        if(!(accountInfo.getEmail().equals(""))) {
            mail.setText(accountInfo.getEmail());
            mail.setVisibility(View.VISIBLE);
            vmail.setVisibility(View.VISIBLE);

        }
        if (!(accountInfo.getPhone().equals(""))) {
            tele.setText(accountInfo.getPhone());
            tele.setVisibility(View.VISIBLE);
            vtele.setVisibility(View.VISIBLE);
        }
        if (!(accountInfo.getLinkedIn().equals(""))) {
            linked.setText(accountInfo.getLinkedIn());
            linked.setVisibility(View.VISIBLE);
            vlinked.setVisibility(View.VISIBLE);
        }
        if (!(accountInfo.getSkype().equals(""))) {
            sky.setText(accountInfo.getSkype());
            sky.setVisibility(View.VISIBLE);
            vsky.setVisibility(View.VISIBLE);
        }
    }
}

