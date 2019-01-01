package com.drrabny.darrbyproject.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.Addlanguage;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddLanguageFragment extends Fragment {


    PrefConfig prefConfig;
    Context context;
    EditText editText;
    Dialog progressDialog;
    private void ShowWaiting() {
        progressDialog = new Dialog(getActivity());
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.wait_dialog);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
    RatingBar ratingBar;
    Button button_add;
    private ApiInterface apiInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_language, container, false);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        final String token=prefConfig.readToken();
        editText=(EditText)v.findViewById(R.id.lang_name);
        ratingBar=(RatingBar)v.findViewById(R.id.lang_rate);
        button_add=(Button)v.findViewById(R.id.lang_add);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
ShowWaiting();
                Call<Addlanguage> call=apiInterface.ADDLANGUAGE_CALL("Bearer "+token ,editText.getText().toString(),  (int)ratingBar.getRating());
                call.enqueue(new Callback<Addlanguage>() {
                    @Override
                    public void onResponse(Call<Addlanguage> call, Response<Addlanguage> response) {
                       progressDialog.dismiss();
                        Log.e("ggg",response.code()+"");
                        Log.e("jjj",response.body().getStatus()+"");
                        Toast.makeText(getActivity(), "Added Operation is Done",
                                Toast.LENGTH_LONG).show();
                        LanguagesFragment languagesFragment=new LanguagesFragment();
                        FragmentManager manager=getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.content_frame,languagesFragment,languagesFragment.getTag())
                                .commit();
                    }

                    @Override
                    public void onFailure(Call<Addlanguage> call, Throwable t) {
progressDialog.dismiss();
                    }
                });
            }
        });


        return v;

    }


}
