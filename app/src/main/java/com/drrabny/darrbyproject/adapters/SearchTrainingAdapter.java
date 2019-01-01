package com.drrabny.darrbyproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.Student;
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.TraningPost;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchTrainingAdapter  extends RecyclerView.Adapter<SearchTrainingAdapter.ViewHolder>{

    List<TraningPost> traningPosts ;
    Context context ;


    public SearchTrainingAdapter(List<TraningPost> studentList, Context context ) {
        this.traningPosts = studentList;
        this.context = context;
    }

    @Override
    public SearchTrainingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row , parent , false) ;
        return new SearchTrainingAdapter.ViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final SearchTrainingAdapter.ViewHolder holder, final int position) {
        holder.name.setText(traningPosts.get(position).getTitle());
        holder.phone.setText(traningPosts.get(position).getCoverLetter());
        Picasso.with(context).load(traningPosts.get(position).getImg()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return traningPosts.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView name , phone , Dob ;
        @SuppressLint("ResourceAsColor")
        public ViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.im_exp);
            name = view.findViewById(R.id.txt_exp_title);
            phone = view.findViewById(R.id.txt_exp_describtion);
            Dob = view.findViewById(R.id.txt_exp_time);
        }
    }





}
