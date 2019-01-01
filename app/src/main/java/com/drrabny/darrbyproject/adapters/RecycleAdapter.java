package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.Post;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecycleAdapter extends RecyclerView.Adapter <RecycleAdapter.MyViewHolder>{
    List<Post> posts;
    Context context;


    public RecycleAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_row,parent,false);
        return new RecycleAdapter.MyViewHolder(view,context,posts);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.comp_name.setText(posts.get(position).getTitle());
        holder.time1.setText(posts.get(position).getCreatedAt());
        holder.descrption.setText(posts.get(position).getCoverLetter());

        Picasso.with(context)
                .load(posts.get(position).getImg())
                .into(holder.image1);

        Picasso.with(context)
                .load(posts.get(position).getImg())
                .into(holder.image2);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView comp_name,time1,descrption;
        ImageView image1,image2;
        public MyViewHolder(View itemView ,  Context context, List<Post> posts) {
            super(itemView);
            comp_name=itemView.findViewById(R.id.txt_trainingPost_companyName);
            time1=(TextView)itemView.findViewById(R.id.txt_trainingPosts_duration);
            descrption=(TextView)itemView.findViewById(R.id.txt_trainningPost_coverLetter);
            image1=(ImageView)itemView.findViewById(R.id.im_trainingPost_companyPhoto);
            image2=(ImageView)itemView.findViewById(R.id.im_trainningPost);


        }
    }
}
