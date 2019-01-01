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
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.Company;
import com.drrabny.darrbyproject.pojoClasses.ResponserSearch.Student;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchCompanyAdapter  extends RecyclerView.Adapter<SearchCompanyAdapter.ViewHolder>{

    List<Company> companies ;
    Context context ;


    public SearchCompanyAdapter(List<Company> companyList, Context context ) {
        this.companies = companyList;
        this.context = context;
    }

    @Override
    public SearchCompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_row , parent , false) ;
        return new SearchCompanyAdapter.ViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final SearchCompanyAdapter.ViewHolder holder, final int position) {
        holder.name.setText(companies.get(position).getCompanyName());
        holder.phone.setText(companies.get(position).getCity()+" "+companies.get(position).getCountry());
        Picasso.with(context).load(companies.get(position).getPP()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return companies.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage;
        TextView name , phone , Dob ;
        public ViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.im_exp);
            name = view.findViewById(R.id.txt_exp_title);
            phone = view.findViewById(R.id.txt_exp_describtion);
            Dob = view.findViewById(R.id.txt_exp_time);
        }
    }





}
