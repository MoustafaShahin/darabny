package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drrabny.darrbyproject.pojoClasses.Experience;
import com.drrabny.darrbyproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahmad on 25-Apr-18.
 */

public class RecyExpereinceAdapter extends RecyclerView.Adapter<RecyExpereinceAdapter.MyViewHolder> {

    Context context;
    List<Experience> experiences;

    public RecyExpereinceAdapter(Context context, List<Experience> experiences) {
        this.context = context;
        this.experiences = experiences;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expereince_row,parent,false);
        return new RecyExpereinceAdapter.MyViewHolder(view,context,experiences);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtTitle.setText(experiences.get(position).getTitle());
        holder.txtDesc.setText(experiences.get(position).getDescription());
        holder.txtTime.setText(experiences.get(position).getStart());

        Picasso.with(context).load(experiences.get(position).getImg()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtDesc,txtTime;
        ImageView imageView;

        public MyViewHolder(View itemView, Context context, List<Experience> experiences) {
            super(itemView);

            imageView=itemView.findViewById(R.id.im_exp);
            txtTitle=itemView.findViewById(R.id.txt_exp_title);
            txtDesc=itemView.findViewById(R.id.txt_exp_describtion);
            txtTime=itemView.findViewById(R.id.txt_exp_time);

        }


    }
}
