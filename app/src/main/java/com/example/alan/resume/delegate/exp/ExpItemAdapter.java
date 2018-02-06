package com.example.alan.resume.delegate.exp;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.resume.R;
import com.example.alan.resume.entity.ExpInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ExpItemAdapter extends RecyclerView.Adapter<ExpItemAdapter.MyViewHolder>{

    private List<ExpInfo> expInfos;
    private LayoutInflater inflater;

    public ExpItemAdapter(List<ExpInfo> infos,Context context){
        this.expInfos = infos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_multiple_exp_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ExpInfo info = expInfos.get(position);
        holder.mTextExpTime.setText(info.getStartTime()+"-"+info.getEndTime());
        holder.mTextExpCompany.setText(info.getCompany());
        holder.mTextExpJob.setText(info.getJob());
    }


    @Override
    public int getItemCount() {
        return expInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mTextExpTime;
        AppCompatTextView mTextExpCompany;
        AppCompatTextView mTextExpJob;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextExpTime = itemView.findViewById(R.id.tv_exp_time_item);
            mTextExpCompany = itemView.findViewById(R.id.tv_exp_company_item);
            mTextExpJob = itemView.findViewById(R.id.tv_exp_job_item);
        }
    }
}
