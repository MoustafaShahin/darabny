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
import com.drrabny.darrbyproject.pojoClasses.Following;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ahmad on 25-Apr-18.
 */

public class RecyFollwingAdapter extends RecyclerView.Adapter<RecyFollwingAdapter.MyViewHolder> {

    Context context;
    List<Following> followings;

    public RecyFollwingAdapter(Context context, List<Following> followings) {
        this.context = context;
        this.followings = followings;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expereince_row,parent,false);
        return new RecyFollwingAdapter.MyViewHolder(view,context,followings);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtTitle.setText(followings.get(position).getName());
        Picasso.with(context).load(followings.get(position).getLogo()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return followings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle,txtDesc,txtTime;
        ImageView imageView;

        public MyViewHolder(View itemView, Context context, List<Following> followings) {
            super(itemView);
            imageView =itemView.findViewById(R.id.im_exp);
            txtTitle=itemView.findViewById(R.id.txt_exp_title);
            txtDesc=itemView.findViewById(R.id.txt_exp_describtion);
            txtTime=itemView.findViewById(R.id.txt_exp_time);

        }


    }
}
