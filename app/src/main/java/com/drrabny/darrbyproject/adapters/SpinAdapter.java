package com.drrabny.darrbyproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drrabny.darrbyproject.R;
import com.drrabny.darrbyproject.pojoClasses.ResponseSkills.Skill;

import java.util.List;

public class SpinAdapter extends BaseAdapter {
    Context context;
    List<Skill> skills;

    public SpinAdapter(Context context, List<Skill> patientClassList) {
        this.context = context;
        this.skills = patientClassList;
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public Object getItem(int i) {
        return skills.get(i);
    }

    @Override
    public long getItemId(int i) {
        if (skills.size()==0)
            return 0;
        else
            return skills.indexOf(skills.get(i));
    }

    class ViewHolder
    {
        TextView PatientName;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        SpinAdapter.ViewHolder holder=null;

        LayoutInflater mIFlater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View row = mIFlater.inflate(R.layout.view_spinner,viewGroup,false);

        holder=new SpinAdapter.ViewHolder();

        holder.PatientName=row.findViewById(R.id.spinnerView);

        holder.PatientName.setText(skills.get(i).getTitle());
        return row;

    }
}
