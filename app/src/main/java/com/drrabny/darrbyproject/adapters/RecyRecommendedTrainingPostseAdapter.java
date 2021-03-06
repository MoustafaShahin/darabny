package com.drrabny.darrbyproject.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.drrabny.darrbyproject.Fragments.ApplyFragment;
import com.drrabny.darrbyproject.Fragments.CompFucFragment;
import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.$recommendedTrainingPost;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahmad on 25-Apr-18.
 */

public class RecyRecommendedTrainingPostseAdapter extends RecyclerView.Adapter<RecyRecommendedTrainingPostseAdapter.MyViewHolder> {

    Context context;
    List<$recommendedTrainingPost> $recommendedTrainingPosts;

    public RecyRecommendedTrainingPostseAdapter(Context context, List<$recommendedTrainingPost> $recommendedTrainingPosts) {
        this.context = context;
        this.$recommendedTrainingPosts = $recommendedTrainingPosts;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row,parent,false);
        return new RecyRecommendedTrainingPostseAdapter.MyViewHolder(view,context,$recommendedTrainingPosts);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        
        holder.txtCover.setText($recommendedTrainingPosts.get(position).getCoverLetter());
        holder.txtCompanyName.setText($recommendedTrainingPosts.get(position).getCompanies().getName());
        holder.txtDuration.setText($recommendedTrainingPosts.get(position).getTime_ago());
        Picasso.with(context).load($recommendedTrainingPosts.get(position).getImg()).into(holder.imageView);
        Picasso.with(context).load($recommendedTrainingPosts.get(position).getCompanies().getLogo()).into(holder.imgCompany);

        final int id = $recommendedTrainingPosts.get(position).getCompanyId();
        holder.txtCompanyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {openCompany(view,id);
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
        return $recommendedTrainingPosts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtCover,txtCompanyName,txtDuration;
        ImageView imageView,imgCompany;
        Button btn_apply;

        public MyViewHolder(View itemView, Context context, List<$recommendedTrainingPost> trainingPosts) {
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
