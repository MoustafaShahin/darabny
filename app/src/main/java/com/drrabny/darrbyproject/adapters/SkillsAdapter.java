package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.DeleteSkill;
import com.drrabny.darrbyproject.pojoClasses.Skill;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MyViewHolder> {

    List<Skill>skills;
    Context context;
    PrefConfig prefConfig;
    private ApiInterface apiInterface;

    public SkillsAdapter(List<Skill> skills, Context context) {
        this.skills = skills;
        this.context = context;
    }

    @Override
    public SkillsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_item,parent,false);
        return new SkillsAdapter.MyViewHolder(view,context,skills);
    }

    @Override
    public void onBindViewHolder(SkillsAdapter.MyViewHolder holder, final int position) {
        holder.desc.setText(skills.get(position).getTitle());

        //delete skills
        holder.imageButtondelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
                prefConfig=new PrefConfig(context);
                final String token=prefConfig.readToken();
                Call<DeleteSkill> call=apiInterface.DELETE_SKILL_CALL("Bearer "+token , skills.get(position).getId());
                call.enqueue(new Callback<DeleteSkill>() {
                    @Override
                    public void onResponse(Call<DeleteSkill> call, Response<DeleteSkill> response) {
                        skills.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<DeleteSkill> call, Throwable t) {

                    }
                });
            }
        });

    }
    @Override
    public int getItemCount() {
        return skills.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        ImageButton imageButtondelet;
        public MyViewHolder(View itemView , Context context, List<Skill> skills) {
            super(itemView);
           desc=(TextView) itemView.findViewById(R.id.txt_skills);
           imageButtondelet=(ImageButton)itemView.findViewById(R.id.img_skills);

        }
    }
}
