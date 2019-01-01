package com.drrabny.darrbyproject.Fragments;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.adapters.RecyFollwingAdapter;
import com.drrabny.darrbyproject.adapters.RecycleAdapter;
import com.drrabny.darrbyproject.pojoClasses.Company;
import com.drrabny.darrbyproject.pojoClasses.Following;
import com.drrabny.darrbyproject.pojoClasses.Post;
import com.drrabny.darrbyproject.pojoClasses.ResponseFollow;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drrabny.darrbyproject.R.color.barrbny;
import static com.drrabny.darrbyproject.R.color.white;


public class CompFucFragment extends Fragment {

    Context context;
    private ApiInterface apiInterface;
    PrefConfig prefConfig;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecycleAdapter adapter;
    String token;
    int ids;
    private List<Post> posts;
    private String follow_flag;
    TextView fcu_name,fcu_name1,fcu_desc,follow;
    CircleImageView circleImageView;
    Button follow1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();
        prefConfig=new PrefConfig(context);
          token=prefConfig.readToken();
        View view= inflater.inflate(R.layout.fragment_comp_fuc, container, false);

        circleImageView=(CircleImageView)view.findViewById(R.id.img_fcu);
        fcu_name=(TextView)view.findViewById(R.id.txt_fcu);
        fcu_name1=(TextView)view.findViewById(R.id.txt_fcu1);
        fcu_desc=(TextView)view.findViewById(R.id.txt_fcu_desc);
        follow=(TextView)view.findViewById(R.id.txt_follow_num);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleView);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        follow1=(Button)view.findViewById(R.id.follow_btn);
        follow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               follow(view);
            }
        });


        //setup progress bar before call
        final ProgressDialog progressDialog =new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Page is Loading.......");
        progressDialog.show();

        Bundle bundle=getArguments();
        int id =0;
        if (bundle != null){
             id =bundle.getInt("ID");
        }

        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Company> call=apiInterface.COMPANY_CALL("Bearer "+token,id);
        call.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {

                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                ids = response.body().getCompany().getId();
                Log.e("ff",response.code()+"");
                fcu_name.setText(response.body().getCompany().getCompanyName());
                fcu_name1.setText(response.body().getCompany().getEmail());
                fcu_desc.setText(response.body().getCompany().getDescription());
                follow.setText(response.body().getNumberOfFollowers()+"");
                follow_flag = response.body().getFollowStatus();

                if(follow_flag.equals("true")){
                    follow1.setText("Unfollow");
                }else{
                    follow1.setText("follow");
                }

                Picasso.with(context)
                        .load(response.body().getCompany().getPP())
                        .into(circleImageView);
                //To handel adapter
                posts=response.body().getPosts();
                adapter=new RecycleAdapter(context,posts);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {

                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Log.e("noo","");
            }
        });
        return  view;
    }

    private void follow(View view) {

        if(follow_flag.equals("true")){
            removeFollow(ids);

        }else{

            addFollow(ids);
        }

    }
    private void addFollow(int id){
        Call<ResponseFollow>followCall = apiInterface.RESPONSE_FOLLOW_CALL("Bearer "+token,id);
        followCall.enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("true")){
                        follow1.setText("Unfollow");
                        follow_flag = "true";

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {

            }
        });
    }
    private void removeFollow(int id){
        Call<ResponseFollow>followCall = apiInterface.removeFollow("Bearer "+token,id);
        followCall.enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("true")){
                        follow1.setText("follow");
                        follow_flag = "false";

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {

            }
        });
    }
}
