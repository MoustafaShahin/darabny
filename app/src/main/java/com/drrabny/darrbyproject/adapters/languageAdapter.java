package com.drrabny.darrbyproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.SharedPrefrence.PrefConfig;
import com.drrabny.darrbyproject.pojoClasses.DeleteLanguage;
import com.drrabny.darrbyproject.pojoClasses.DeleteSkill;
import com.drrabny.darrbyproject.pojoClasses.Language;
import com.drrabny.darrbyproject.retrofit.ApiClient;
import com.drrabny.darrbyproject.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class languageAdapter extends  RecyclerView.Adapter<languageAdapter.MyViewHolder> {
    List<Language> languages;
    Context context;
    ApiInterface apiInterface;
    PrefConfig prefConfig;
    String token;
    public languageAdapter(List<Language> languages, Context context) {
        this.languages = languages;
        this.context = context;
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig=new PrefConfig(context);
        token=prefConfig.readToken();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.language_item,parent,false);
        return new MyViewHolder(view,context,languages);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.laguage_name.setText(languages.get(position).getLanguage());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(languages.get(position).getId());
                languages.remove(position);
                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView laguage_name;
        ImageButton imageButton;
        public MyViewHolder(View itemView , Context context, List<Language> languages) {
            super(itemView);

            laguage_name=(TextView)itemView.findViewById(R.id.txt_language);
            imageButton=itemView.findViewById(R.id.deleteLanguage);

        }
    }
    private void remove(int id){
    Call<DeleteLanguage>deleteLanguageCall = apiInterface.DELETE_LANGUAGE_CALL("Bearer "+token,id);
    deleteLanguageCall.enqueue(new Callback<DeleteLanguage>() {
        @Override
        public void onResponse(Call<DeleteLanguage> call, Response<DeleteLanguage> response) {

        }

        @Override
        public void onFailure(Call<DeleteLanguage> call, Throwable t) {

        }
    });
    }
}
