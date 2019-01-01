package com.drrabny.darrbyproject.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.AccountInfo;
import com.drrabny.darrbyproject.pojoClasses.UpdateStudentAcountInformation;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {


    PrefConfig prefConfig;
    Context context;
    ApiInterface apiInterface;
    EditText username,email,password;
    Button Save;
    AccountInfo accountInfo;
    String token;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_account, container, false);
        initview();
        setdata();
        onclick();
        return v;
    }
    private void initview(){
        context=getActivity();
        //initialize sharedPrefrence
        prefConfig=new PrefConfig(context);
        //initialize apiInterface to class
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ///tokin
        token=prefConfig.readToken();
        // Inflate the layout for this fragment
        username =v.findViewById(R.id.accoubtUserName_txt);
        email=v.findViewById(R.id.accoubtEmail_txt);
        password=v.findViewById(R.id.accoubtconfirm_txt);
        Save=v.findViewById(R.id.saveAccountInfo);
    }
    private void setdata(){
        Call<AccountDetail> accountDetailCall= apiInterface.ACCOUNT_DETAIL_CALL("Bearer "+token);
        accountDetailCall.enqueue(new Callback<AccountDetail>() {
            @Override
            public void onResponse(Call<AccountDetail> call, Response<AccountDetail> response) {

                if (response.isSuccessful()) {
                    accountInfo = response.body().getAccountInfo();
                    setViews();
                     //set data in view
                }else {
                }
            }

            @Override
            public void onFailure(Call<AccountDetail> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setViews(){
        username.setText(accountInfo.getUsername());
        email.setText(accountInfo.getEmail());

    }
    private void onclick(){
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAccountInfo(view);
            }
        });
    }
    private void SaveAccountInfo(View v){
        String Email,UserName,Password;
        UserName=username.getText().toString();
        Email=email.getText().toString();
        Password=password.getText().toString();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("this field is required");
            email.setFocusableInTouchMode(true);
            email.requestFocus();
        } else if (UserName.length() == 0) {
            username.setError("this field is required");
            username.setFocusableInTouchMode(true);
            username.requestFocus();
        } else if (Password.length() == 0) {
            password.setError("this field is required");
            password.setFocusableInTouchMode(true);
            password.requestFocus();
        } else
        {
            Call<UpdateStudentAcountInformation> call = apiInterface.UPDATE_STUDENT_ACOUNT_INFORMATION_CALL
                    ("Bearer "+token,Email,UserName,Password);
            call.enqueue(new Callback<UpdateStudentAcountInformation>() {
                @Override
                public void onResponse(Call<UpdateStudentAcountInformation> call,
                                       retrofit2.Response<UpdateStudentAcountInformation> response) {
                    prefConfig.makeToast("upppppppppppppppp");

                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals("true")) {
                            Log.e("yes", response.code() + "");
                            prefConfig.makeToast("updata is done");
                            SettingFragment settingFragment=new SettingFragment();
                            FragmentManager manager=getFragmentManager();
                            manager.beginTransaction()
                                    .replace(R.id.content_frame,settingFragment,settingFragment.getTag()).addToBackStack(null)
                                    .commit();

                        }
                    } else {
                        Log.e("yes", response.code() + "");
                        prefConfig.makeToast(response.message());
                        password.setText("");
                    }
                }

                @Override
                public void onFailure(Call<UpdateStudentAcountInformation> call, Throwable t) {
                    Log.e("kkkkkkk", call.request().toString());
                    prefConfig.makeToast("The connection lose");
                }
            });
        }}


}
