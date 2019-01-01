package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.$recmondedcomp;
import com.drrabny.darrbyproject.pojoClasses.ResponseFollow;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmad on 25-Apr-18.
 */

public class RecyRecommendedCompanyAdapter extends RecyclerView.Adapter<RecyRecommendedCompanyAdapter.MyViewHolder> {

    Context context;
    List<$recmondedcomp> $recmondedcomps;
    ApiInterface apiInterface;
    PrefConfig prefConfig;
            String token;
    public RecyRecommendedCompanyAdapter(Context context, List<$recmondedcomp> $recmondedcomps) {
        this.context = context;
        this.$recmondedcomps = $recmondedcomps;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_company, parent, false);
        return new RecyRecommendedCompanyAdapter.MyViewHolder(view, context, $recmondedcomps);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Picasso.with(context).load($recmondedcomps.get(position).getLogo()).into(holder.imgCompany);
        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFollow($recmondedcomps.get(position).getCompanyId());
                $recmondedcomps.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                $recmondedcomps.remove(position);
notifyDataSetChanged();
            }
        });
    }
    private void addFollow(int id){
        Call<ResponseFollow> followCall = apiInterface.RESPONSE_FOLLOW_CALL("Bearer "+token,id);
        followCall.enqueue(new Callback<ResponseFollow>() {
            @Override
            public void onResponse(Call<ResponseFollow> call, Response<ResponseFollow> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equals("true")){


                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseFollow> call, Throwable t) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return $recmondedcomps.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageButton imgClose;
        ImageView imgCompany;
        Button btn_follow;

        public MyViewHolder(View itemView, Context context, List<$recmondedcomp> $recmondedcomps) {
            super(itemView);
            prefConfig=new PrefConfig(context);
            token=prefConfig.readToken();
            apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
            imgClose = itemView.findViewById(R.id.img_recommended_company_close);
            imgCompany = itemView.findViewById(R.id.img_recommended_company);
            btn_follow = itemView.findViewById(R.id.btn_recommended_company_follow);

        }


    }
}