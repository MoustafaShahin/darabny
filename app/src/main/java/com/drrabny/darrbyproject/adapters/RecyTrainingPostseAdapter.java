package com.drrabny.darrbyproject.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.drrabny.darrbyproject.Fragments.ApplyFragment;
import com.drrabny.darrbyproject.Fragments.CompFucFragment;
import com.drrabny.darrbyproject.pojoClasses.TrainingPost;
import com.drrabny.darrbyproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
* Created by ahmad on 25-Apr-18.
*/

public class RecyTrainingPostseAdapter extends RecyclerView.Adapter<RecyTrainingPostseAdapter.MyViewHolder> {

    Context context;
    List<TrainingPost> trainingPosts;

    public RecyTrainingPostseAdapter(Context context, List<TrainingPost> trainingPosts) {
        this.context = context;
        this.trainingPosts = trainingPosts;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row,parent,false);
        return new RecyTrainingPostseAdapter.MyViewHolder(view,context,trainingPosts);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txtCover.setText(trainingPosts.get(position).getCoverLetter());
        holder.txtCompanyName.setText(trainingPosts.get(position).getCompanies().getName());
        holder.txtDuration.setText(trainingPosts.get(position).getDuration());
        Picasso.with(context).load(trainingPosts.get(position).getImg()).into(holder.imageView);
        Picasso.with(context).load(trainingPosts.get(position).getCompanies().getLogo()).into(holder.imgCompany);
        holder.txtDuration.setText(trainingPosts.get(position).getTime_ago());

        final int id = trainingPosts.get(position).getCompanyId();
        holder.txtCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCompany(view,id);
            }
        });
        holder.imgCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCompany(view,id);
            }
        });

        holder.btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplyFragment applyFragment=new ApplyFragment();
                FragmentManager manager= ((Activity)context).getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.content_frame,applyFragment,applyFragment.getTag()).addToBackStack(null)
                        .commit();
            }
        });

    }

    private void openCompany(View view,int id) {
        CompFucFragment company=new CompFucFragment();
        Bundle bundle= new Bundle();
        bundle.putInt("ID", id);
        company.setArguments(bundle);
        FragmentManager manager= ((Activity)context).getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.content_frame,company,company.getTag()).addToBackStack(null)
                .commit();
    }

    @Override
    public int getItemCount() {
        return trainingPosts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCover,txtCompanyName,txtDuration;
        ImageView imageView,imgCompany;
        Button btn_apply;

        public MyViewHolder(View itemView, Context context, List<TrainingPost> trainingPosts) {
            super(itemView);

            imageView=itemView.findViewById(R.id.im_trainningPost);
            imgCompany=itemView.findViewById(R.id.im_trainingPost_companyPhoto);
            txtCompanyName=itemView.findViewById(R.id.txt_trainingPost_companyName);
            txtCover=itemView.findViewById(R.id.txt_trainningPost_coverLetter);
            txtDuration =itemView.findViewById(R.id.txt_trainingPosts_duration);
            btn_apply =itemView.findViewById(R.id.btn_feed_apply);


        }


    }
}
