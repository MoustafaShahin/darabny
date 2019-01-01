package com.drrabny.darrbyproject.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.UserLogin;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    PrefConfig prefConfig;
    ApiInterface apiInterface;
    EditText txtEmail,txtPass;
    private Dialog progressDialog;

    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //to no automatic show keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // to remove action bar from activity
        getSupportActionBar().hide();
        //initialize sharedPrefrence
        prefConfig=new PrefConfig(this);
        //initialize apiInterface to class
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        // link views on code
        txtEmail =findViewById(R.id.txt_email);
        txtPass=findViewById(R.id.txt_pass);
        btnlogin =findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin(view);
            }
        });


    }

    private void btnLogin(View view) {

        String email=txtEmail.getText().toString();
        String pass=txtPass.getText().toString();
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("this field is required");
            txtEmail.setFocusableInTouchMode(true);
            txtEmail.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            txtPass.setError("this field is required");
            txtPass.setFocusableInTouchMode(true);
            txtPass.requestFocus();
        }else {
            ShowWaiting();
            Call<UserLogin> call = apiInterface.LOGIN_RESULT_CALL(email, pass);
            call.enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, retrofit2.Response<UserLogin> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Log.e("yes", response.code() + "");
                        String token = response.body().getResponse().getToken();
                        prefConfig.writeToken(token);
                        prefConfig.writeLoginStatus(true);
                        Log.e("d",token);
                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                        LoginActivity.this.finish();
                    } else {
                        Log.e("yes", response.code() + "");
                        prefConfig.writeToken(null);
                        prefConfig.writeLoginStatus(false);
                        prefConfig.makeToast("Email Or Password is incorrect");
                        txtPass.setText("");
                    }
                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                    Log.e("kkkkkkk", call.request().toString());
                    prefConfig.makeToast("The connection lose");
                }
            });
        }
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,SignUp_Activity.class));

    }
    private void ShowWaiting() {
        progressDialog = new Dialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
