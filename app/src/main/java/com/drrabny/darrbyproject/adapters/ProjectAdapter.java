package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.Project;

import java.util.List;

/* Create By Mohamed Abd Elkarim */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyViewHolder> {

    List<Project> projects;
    Context context;


    public ProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }


    @Override
    public ProjectAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_item,parent,false);
        return new ProjectAdapter.MyViewHolder(view,context,projects);
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.MyViewHolder holder, int position) {
        holder.project_name.setText(projects.get(position).getName());
        holder.project_title.setText(projects.get(position).getCoverLetter());
        holder.traneeNO.setText(projects.get(position).getDateFrom());
        holder.pivot.setText(projects.get(position).getDateTo());
        holder.createAT.setText(projects.get(position).getDateFrom());
        holder.endAT.setText(projects.get(position).getDateTo());


    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView project_name,project_title,traneeNO,pivot,createAT,endAT;
        public MyViewHolder(View itemView , Context context, List<Project> projects) {
            super(itemView);
            project_name=(TextView)itemView.findViewById(R.id.projct_name);
            project_title=(TextView)itemView.findViewById(R.id.project_title);
            traneeNO=(TextView)itemView.findViewById(R.id.project_traneeNO);
            pivot=(TextView)itemView.findViewById(R.id.project_pivot);
            createAT=(TextView)itemView.findViewById(R.id.project_createAt);
            endAT=(TextView)itemView.findViewById(R.id.project_endAT);



        }
    }
}
