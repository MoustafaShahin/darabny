package com.drrabny.darrbyproject.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.ResponseRegister.ResponseRegister;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp_Activity extends AppCompatActivity {


    PrefConfig prefConfig;
    Context context;
    ApiInterface apiInterface;
    String token;
    private DatePickerDialog datePickerDialog;
    Button Reg_btn;
    RadioGroup g;
    ImageView imProfile;
    private Dialog progressDialog;
    ImageButton imEdite;
    TextView dayOfBirth;
    EditText firstName, lastName, userName, passwordReg, rePassword, emailReg;
    //profile  photo which selected
    Bitmap selectedImage;
    //for check change image or not
    Boolean image_change = false;
    String Gender="male";
    String FirstName, LastName, DayOfBirth, UserName, PasswordReg, RePassword, EmailReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        context = getApplicationContext();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // to remove action bar from activity
        getSupportActionBar().hide();
        //initialize sharedPrefrence
        prefConfig = new PrefConfig(this);
        //initialize apiInterface to class
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        ///tokin
        token = prefConfig.readToken();
        g = findViewById(R.id.rg);
        g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId()==R.id.male)
                {
                    Gender = "male";
                }else{
                    Gender = "female";
                }
            }
        });
        imProfile = findViewById(R.id.im_register_profile);
        imEdite = findViewById(R.id.im_register_edit);
        Reg_btn = findViewById(R.id.btn_reg);
        firstName = findViewById(R.id.firstName_txt);
        lastName = findViewById(R.id.lastName_txt);
        dayOfBirth = findViewById(R.id.birthDay_txt);
        dayOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        userName = findViewById(R.id.userName_txt);
        passwordReg = findViewById(R.id.passwordReg_txt);
        rePassword = findViewById(R.id.RePasswordReg_txt);
        emailReg = findViewById(R.id.emailReg_txt);

        Reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register_btn(view);
            }
        });

        imEdite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int RESULT_LOAD_IMG = 500;
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
    }

    ///////////
    private void Register_btn(View view) {

        FirstName = firstName.getText().toString();
        LastName = lastName.getText().toString();
        DayOfBirth = dayOfBirth.getText().toString();
        UserName = userName.getText().toString();
        PasswordReg = passwordReg.getText().toString();
        RePassword = rePassword.getText().toString();
        EmailReg = emailReg.getText().toString();

        if (FirstName.length() == 0) {
            firstName.setError("this field is required");
            firstName.setFocusableInTouchMode(true);
            firstName.requestFocus();
            return;
        }
        if (LastName.length() == 0) {
            lastName.setError("this field is required");
            lastName.setFocusableInTouchMode(true);
            lastName.requestFocus();
            return;
        }
        if (DayOfBirth.length() == 0) {
            dayOfBirth.setError("this field is required");
            dayOfBirth.setFocusableInTouchMode(true);
            dayOfBirth.requestFocus();
            return;
        }
        if (UserName.length() == 0) {
            userName.setError("this field is required");
            userName.setFocusableInTouchMode(true);
            userName.requestFocus();
            return;
        }
        if (PasswordReg.length() == 0) {
            passwordReg.setError("this field is required");
            passwordReg.setFocusableInTouchMode(true);
            passwordReg.requestFocus();
            return;
        }
        if (RePassword.length() == 0) {
            rePassword.setError("this field is required");
            rePassword.setFocusableInTouchMode(true);
            rePassword.requestFocus();
            return;
        }
        if (EmailReg.length() == 0) {
            emailReg.setError("this field is required");
            emailReg.setFocusableInTouchMode(true);
            emailReg.requestFocus();
            return;
        }
        if (selectedImage == null) {
            Toast.makeText(this, "must add profile pic", Toast.LENGTH_LONG).show();
            return;
        }
         if (!PasswordReg.equals(RePassword)) {
            Toast.makeText(this, "Your passwords must matching", Toast.LENGTH_LONG).show();
                return;
        }
        ShowWaiting();
            regesterccall();
    }
private void regesterccall(){
    Call<ResponseRegister> responseRegisterCall = apiInterface.STUDENT_REGISTER_CALL(FirstName,LastName,Gender,EmailReg,DayOfBirth,UserName,PasswordReg,RePassword,
        "student",getStringImage(selectedImage));
    responseRegisterCall.enqueue(new Callback<ResponseRegister>() {
        @Override
        public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
            progressDialog.dismiss();
            if(response.isSuccessful()){
                if(response.body().getStatus().equals("true")){
                 startActivity(new Intent(SignUp_Activity.this,LoginActivity.class));
                 finish();
                }
                else{
                }
            }else {
                Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseRegister> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}





    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK&& reqCode ==500) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                //set image on image view
                imProfile.setImageBitmap(selectedImage);
                //change value to known that image change
                image_change = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                prefConfig.makeToast("Something went wrong");
            }

        }else {
            prefConfig.makeToast("You haven't picked Image");
        }
    }

    //convert image into string base64
    public String getStringImage(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        return Base64.encodeToString(byteFormat, Base64.DEFAULT);


    }
    private void showDialog() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dayOfBirth.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        //datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }
    public void btn_goto_Login(View view) {
        startActivity(new Intent(this,LoginActivity.class));
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


