package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.Experience;

import java.util.List;

/* Create By Mohamed Abd Elkarim */

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder>{
    List<Experience>experiences;
    Context context;

    public ExperienceAdapter(List<Experience> experiences, Context context) {
        this.experiences = experiences;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_item,parent,false);
        return new ExperienceAdapter.MyViewHolder(view,context,experiences);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.date_exp.setText(experiences.get(position).getCreatedAt());
        holder.language.setText(experiences.get(position).getTitle());
        holder.descrpion.setText(experiences.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date_exp,language,descrpion;
        public MyViewHolder(View itemView , Context context, List<Experience> experiences) {
            super(itemView);
            date_exp=(TextView)itemView.findViewById(R.id.txt_exp_date);
            language=(TextView)itemView.findViewById(R.id.txt_exp_Language);
            descrpion=(TextView)itemView.findViewById(R.id.txt_exp_desc);

        }
    }
}
