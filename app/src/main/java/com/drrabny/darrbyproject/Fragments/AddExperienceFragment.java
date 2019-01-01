package com.drrabny.darrbyproject.Fragments;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.AddExperience;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddExperienceFragment extends Fragment {



    PrefConfig prefConfig;
    Context context;
    EditText Exptitle,ExpStart,ExpEnd,ExpDesc;
    Button addExpe;
    private ApiInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_add_experience, container, false);
        context=getActivity();
        prefConfig=new PrefConfig(context);
        final String token=prefConfig.readToken();
        Exptitle=(EditText)v.findViewById(R.id.expTitle);
        ExpStart=(EditText)v.findViewById(R.id.expStart);
        ExpEnd=(EditText)v.findViewById(R.id.expEnd);
        ExpDesc=(EditText)v.findViewById(R.id.expDesption);
        addExpe=(Button)v.findViewById(R.id.addExperience);
        addExpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

                Call<AddExperience> call=apiInterface.ADD_EXPERIENCE_CALL("Bearer "+token ,Exptitle.getText().toString()
                        ,ExpStart.getText().toString(),ExpEnd.getText().toString(),ExpDesc.getText().toString());


                call.enqueue(new Callback<AddExperience>() {
                    @Override
                    public void onResponse(Call<AddExperience> call, Response<AddExperience> response) {
                        Log.e("ggg",response.code()+"");
                        Log.e("jjj",response.body().getStatus()+"");
                        if (TextUtils.isEmpty(Exptitle.getText().toString()) && TextUtils.isEmpty(ExpStart.getText().toString())
                                && TextUtils.isEmpty(ExpEnd.getText().toString()) && TextUtils.isEmpty(ExpDesc.getText().toString())  ){
                            Toast.makeText(getActivity(), "Added Operation is Failed",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {

                            Toast.makeText(getActivity(), "Added Operation is Done",
                                    Toast.LENGTH_LONG).show();
                            ExperianceFragment experianceFragment = new ExperianceFragment();
                            FragmentManager manager = getFragmentManager();
                            manager.beginTransaction()
                                    .replace(R.id.content_frame, experianceFragment, experianceFragment.getTag())
                                    .commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddExperience> call, Throwable t) {

                    }
                });
            }
        });
        return v;
    }
}
