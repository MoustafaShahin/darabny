package com.drrabny.darrbyproject.SharedPrefrence;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.drrabny.darrbyproject.R;

/**
 * Created by ahmad on 12-Apr-18.
 */

public class PrefConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context) {
        this.context = context;
        sharedPreferences =context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(Boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.commit();
    }
    public Boolean readLoginStatus(){
        return  sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }

    public void writeToken(String token){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_token),token);
        editor.commit();
    }

    public String readToken(){
        return sharedPreferences.getString(context.getString(R.string.pref_token),"");
    }

    public void makeToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
