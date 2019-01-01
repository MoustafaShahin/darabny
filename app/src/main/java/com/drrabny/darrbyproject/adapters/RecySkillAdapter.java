package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drrabny.darrbyproject.pojoClasses.Experience;
import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.Skill;

import java.util.List;

/**
 * Created by ahmad on 25-Apr-18.
 */

public class RecySkillAdapter extends RecyclerView.Adapter<RecySkillAdapter.MyViewHolder> {

    Context context;
    List<Skill> skills;

    public RecySkillAdapter(Context context, List<Skill> skills) {
        this.context = context;
        this.skills = skills;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_row,parent,false);
        return new RecySkillAdapter.MyViewHolder(view,context,skills);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtTitle.setText(skills.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;

        public MyViewHolder(View itemView, Context context, List<Skill> skills) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.txt_stProfile_skill);

        }


    }
}
