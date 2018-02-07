package com.example.alan.resume.delegate.edu.item;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.resume.R;
import com.example.alan.resume.entity.EduInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class EduItemAdapter extends RecyclerView.Adapter<EduItemAdapter.MyViewHolder>{

    private List<EduInfo> eduInfos;
    private LayoutInflater inflater;

    public EduItemAdapter(List<EduInfo> info, Context context){
        this.eduInfos = info;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_multiple_edu_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EduInfo info = eduInfos.get(position);
        holder.mTextEduTime.setText(info.getMStartTime()+"-"+info.getMEndTime());
        holder.mTextEduSchool.setText(info.getMSchool());
        holder.mTextEduPro.setText(info.getMPro());
        holder.mTextEduSchoolType.setText(info.getMSchoolType());
    }


    @Override
    public int getItemCount() {
        return eduInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mTextEduTime;
        AppCompatTextView mTextEduSchool;
        AppCompatTextView mTextEduSchoolType;
        AppCompatTextView mTextEduPro;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextEduTime = itemView.findViewById(R.id.tv_edu_time);
            mTextEduSchool = itemView.findViewById(R.id.tv_edu_school);
            mTextEduSchoolType = itemView.findViewById(R.id.tv_edu_school_type);
            mTextEduPro = itemView.findViewById(R.id.tv_edu_school_pro);

        }
    }
}
