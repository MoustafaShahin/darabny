package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.ChangeStudentPassword;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;


public class UpdatePasswordFragment extends Fragment {

    PrefConfig prefConfig;
    Context context;
    ApiInterface apiInterface;
    EditText currentPass,newPass,rePass;
    Button SavePass;
    String token;

    private Dialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_update_password, container, false);
        context=getActivity();
        //initialize sharedPrefrence

        prefConfig=new PrefConfig(context);
        //initialize apiInterface to class
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        ///tokin
        token=prefConfig.readToken();
        currentPass =v.findViewById(R.id.current_pass_txt);
        newPass=v.findViewById(R.id.newPass_txt);
        rePass=v.findViewById(R.id.Re_pass_txt);
        SavePass=v.findViewById(R.id.SaveUpdatePass);

        SavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUpdatePassword(view);
            }
        });
        return v;
    }
    private void SaveUpdatePassword(View v){
        String CurrentPass,NewPass,RePass;
        CurrentPass=currentPass.getText().toString();
        NewPass=newPass.getText().toString();
        RePass=rePass.getText().toString();

        if (CurrentPass.length()==0) {
            currentPass.setError("this field is required");
            currentPass.setFocusableInTouchMode(true);
            currentPass.requestFocus();
        } else if (NewPass.length() == 0) {
            newPass.setError("this field is required");
            newPass.setFocusableInTouchMode(true);
            newPass.requestFocus();
        } else if (RePass.length() == 0) {
            rePass.setError("this field is required");
            rePass.setFocusableInTouchMode(true);
            rePass.requestFocus();
        }  else if (!NewPass.equals(RePass)) {
            rePass.setError("Your passwords must matching");
            rePass.setFocusableInTouchMode(true);
            rePass.requestFocus();
        } else {
            ShowWaiting();
            Call<ChangeStudentPassword> call = apiInterface.CHANGE_STUDENT_PASSWORD_CALL
                    ("Bearer " + token, CurrentPass, NewPass, RePass);
            call.enqueue(new Callback<ChangeStudentPassword>() {
                @Override

                public void onResponse(Call<ChangeStudentPassword> call,
                                       retrofit2.Response<ChangeStudentPassword> response) {
                progressDialog.dismiss();
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equals(true)) {
                            prefConfig.makeToast(response.body().getSuccess());
                            SettingFragment settingFragment = new SettingFragment();
                            FragmentManager manager = getFragmentManager();
                            manager.beginTransaction()
                                    .replace(R.id.content_frame, settingFragment, settingFragment.getTag()).addToBackStack(null)
                                    .commit();

                        }
                    } else {
                        Log.e("yes", response.code() + "");
                        prefConfig.makeToast(response.message());
                        rePass.setText("");
                    }
                }

                @Override
                public void onFailure(Call<ChangeStudentPassword> call, Throwable t) {
                   progressDialog.dismiss();

                    prefConfig.makeToast(t.getMessage());
                }
            });
        }}
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
