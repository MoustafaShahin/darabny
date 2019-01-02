package com.drrabny.darrbyproject.Fragments;


import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.AddExperience;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.Calendar;

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
    private String token;
    private ApiInterface apiInterface;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_experience, container, false);
        initview();
        onclick();

        return v;
    }

    private void initview() {
        context=getActivity();
        prefConfig=new PrefConfig(context);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        token = prefConfig.readToken();
        Exptitle=(EditText)v.findViewById(R.id.expTitle);
        ExpStart=(EditText)v.findViewById(R.id.expStart);
        ExpEnd=(EditText)v.findViewById(R.id.expEnd);
        ExpDesc=(EditText)v.findViewById(R.id.expDesption);
        addExpe=(Button)v.findViewById(R.id.addExperience);

    }

    private void onclick() {
        addExpe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isvalid();
            }
        });
        ExpStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
            }
        });
        ExpEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(-1);
            }
        });
    }

    private void isvalid() {
        if (TextUtils.isEmpty(Exptitle.getText().toString())) {
            Exptitle.setError("this field is required");
            Exptitle.setFocusableInTouchMode(true);
            Exptitle.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ExpStart.getText().toString())) {
            ExpStart.setError("this field is required");
            ExpStart.setFocusableInTouchMode(true);
            ExpStart.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ExpEnd.getText().toString())) {
            ExpEnd.setError("this field is required");
            ExpEnd.setFocusableInTouchMode(true);
            ExpEnd.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ExpDesc.getText().toString())) {
            ExpDesc.setError("this field is required");
            ExpDesc.setFocusableInTouchMode(true);
            ExpDesc.requestFocus();
            return;
        }
        add();
    }

    private void add() {
        Call<AddExperience> call = apiInterface.ADD_EXPERIENCE_CALL("Bearer " + token, Exptitle.getText().toString()
                , ExpStart.getText().toString(), ExpEnd.getText().toString(), ExpDesc.getText().toString());

        call.enqueue(new Callback<AddExperience>() {
            @Override
            public void onResponse(Call<AddExperience> call, Response<AddExperience> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equals("true")) {
                        ExperianceFragment settingFragment = new ExperianceFragment();
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.content_frame, settingFragment, settingFragment.getTag()).addToBackStack(null)
                                .commit();
                    } else {

                    }
                } else {
                    Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddExperience> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog(final int flag) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (flag == 0) {
                            ExpStart.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        } else {
                            ExpEnd.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                        }

                    }
                }, mYear, mMonth, mDay);
        //datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }
}
