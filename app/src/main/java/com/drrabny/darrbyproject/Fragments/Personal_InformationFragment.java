package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.AccountDetail;
import com.drrabny.darrbyproject.pojoClasses.AccountInfo;
import com.drrabny.darrbyproject.pojoClasses.UpdateStudentpersonalInformation;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class Personal_InformationFragment extends Fragment {

    ApiInterface apiInterface;
    EditText fname,lname,jobTitle,country,city,governorate,university,faculty,
            marital_status, military_status,phone,date_of_birth,cover_letter,
            linked,sky,address;
    //profile photo
    ImageView imageView;
    //image edit
    ImageButton im_edit;
    //profile  photo which selected
    Bitmap selectedImage;
    //for check change image or not
    Boolean image_change =false;
    Button save;
    RadioButton radMale,radFemale;
    String token,gender;
    PrefConfig prefConfig;
    Context context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal__information , container, false);
      //  initialize(view);
        ShowWaiting();


        context =getActivity();
        prefConfig =new PrefConfig(context);
        token =prefConfig.readToken();

        //image edit
        im_edit=view.findViewById(R.id.im_setting_edit);
        im_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                im(view);
            }
        });

        imageView =view.findViewById(R.id.im);
        fname =view.findViewById(R.id.txt_updateInfo_fname);
        lname =view.findViewById(R.id.txt_updateInfo_lname);
        jobTitle =view.findViewById(R.id.txt_updateInfo_jobTitle);
        country =view.findViewById(R.id.txt_updateInfo_country);
        city =view.findViewById(R.id.txt_updateInfo_city);
        governorate =view.findViewById(R.id.txt_updateInfo_governorate);
        university =view.findViewById(R.id.txt_updateInfo_university);
        faculty =view.findViewById(R.id.txt_updateInfo_faculty);
        marital_status =view.findViewById(R.id.txt_updateInfo_marital_state);
        military_status =view.findViewById(R.id.txt_updateInfo_military);
        phone =view.findViewById(R.id.txt_updateInfo_phone);
        date_of_birth =view.findViewById(R.id.txt_updateInfo_data);
        cover_letter =view.findViewById(R.id.txt_updateInfo_cover_title);
        linked =view.findViewById(R.id.txt_updateInfo_linked);
        sky =view.findViewById(R.id.txt_updateInfo_sky);
        address =view.findViewById(R.id.txt_updateInfo_address);
        radMale=view.findViewById(R.id.rad_updateInfo_male);
        radMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                gender="male";
            }
        });
        radFemale=view.findViewById(R.id.rad_updateInfo_female);
        radFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                gender="female";
            }
        });
        save=view.findViewById(R.id.btn_updateInfo_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave(view);
            }
        });

        ////
        apiInterface =ApiClient.getApiClient().create(ApiInterface.class);
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
                Log.e("Failure","the request is fail");
                progressDialog.dismiss();
            }
        });

        return view;
    }


    public void initialize(View view) {

    }

    private void onResponseSuccessful(Response<AccountDetail> response) {
        AccountInfo info = response.body().getAccountInfo();
        fname.setText(info.getFirstName());
        lname.setText(info.getLastName());
        jobTitle.setText(info.getJobTitle());
        country.setText(info.getCountry());
        city.setText(info.getCity());
        governorate.setText(info.getGovernorate());
        university.setText(info.getUniversity());
        faculty.setText(info.getFaculty());
        date_of_birth.setText(info.getdOB());
        sky.setText(info.getSkype());
        linked.setText(info.getLinkedIn());
        phone.setText(info.getPhone());
        faculty.setText(info.getFaculty());
        military_status.setText(info.getMilitaryStatus());
        marital_status.setText(info.getStatus());
        address.setText(info.getAddress());
        cover_letter.setText(info.getBio());

        switch (info.getGender()){
            case "male" :
                gender="male";
                radMale.setChecked(true);
                radFemale.setChecked(false);
                break;
            case  "female" :
                gender="female";
                radMale.setChecked(false);
                radFemale.setChecked(true);
                break;
        }
        String profile_photo =info.getpP();
        Picasso.with(context).load(profile_photo).into(imageView);

    }

    private void btnSave(View view) {
        ShowWaiting();
        String Sfname = fname.getText().toString();
        String Slname = lname.getText().toString();
        String SJobTile = jobTitle.getText().toString();
        String SCountry = country.getText().toString();
        String SCity = city.getText().toString();
        String SGovernorate = governorate.getText().toString();
        String SUniversity = university.getText().toString();
        String SFaculty = faculty.getText().toString();
        String SDate_of_birth = date_of_birth.getText().toString();
        String SSky = sky.getText().toString();
        if (SSky.equals(null)){
            SSky="";
        }
        String SLinked = linked.getText().toString();
        if (SLinked.equals(null)){
            SLinked="";
        }
        String SPhone = phone.getText().toString();
        String SMilitary_status = military_status.getText().toString();
        String SMarital_status = marital_status.getText().toString();
        String SAddress = address.getText().toString();
        String Scover =cover_letter.getText().toString();

        if(radMale.isChecked())
            gender="male";
        if (radFemale.isChecked())
            gender="female";

        if (!(image_change)){
            selectedImage =((BitmapDrawable) imageView.getDrawable()).getBitmap();
        }

        final Call<UpdateStudentpersonalInformation> updateStudentpersonalInformationCall=
                apiInterface.UPDATE_STUDENTPERSONAL_INFORMATION_CALL("Bearer "+token,Sfname,
                        Slname,SJobTile,SCountry,SCity,SGovernorate,SUniversity,SFaculty,gender,
                        SMarital_status,SMilitary_status,SPhone,SDate_of_birth,Scover,SLinked,SSky,
                        getStringImage(selectedImage));
        updateStudentpersonalInformationCall.enqueue(new Callback<UpdateStudentpersonalInformation>() {
            @Override
            public void onResponse(Call<UpdateStudentpersonalInformation> call,
                                   Response<UpdateStudentpersonalInformation> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Log.e("yaaaa", response.code() + "");
                    if (response.body().getStatus().equals("true")){
                        prefConfig.makeToast(response.body().getSuccess());
                        SettingFragment Fragment=new SettingFragment();
                        FragmentManager manager=getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.content_frame,Fragment,Fragment.getTag())
                                .commit();
                    }else {
                    /* // updateStudentError(response.body().getError());
                       JsonObject error = response.body().getResponse();
                       if (error.has("name")){
                           Log.e("name","has");
                       }*/
                        Log.e("status",response.body().getResponse()+"");

                    }
                }else {
                    Log.e("not successful request", response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<UpdateStudentpersonalInformation> call, Throwable t) {
              progressDialog.dismiss();
                Log.e("fail in request","failure");
            }
        });

    }



    public void im(View view) {
        int RESULT_LOAD_IMG=500;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    //convert image into string base64
    public String getStringImage(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        return Base64.encodeToString(byteFormat, Base64.NO_WRAP);

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                //set image on image view
                imageView.setImageBitmap(selectedImage);
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


}
