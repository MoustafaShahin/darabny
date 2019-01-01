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
import com.squareup.picasso.Picasso;

import java.util.List;


public class SearchStudentAdapter extends RecyclerView.Adapter<SearchStudentAdapter.ViewHolder>{

    List<Student> students ;
    Context context ;


    public SearchStudentAdapter(List<Student> studentList, Context context ) {
        this.students = studentList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row , parent , false) ;
        return new ViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(students.get(position).getFirstName()+" "+students.get(position).getLastName());
        holder.phone.setText(students.get(position).getPhone());
        Picasso.with(context).load(students.get(position).getPP()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return students.size() ;
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
