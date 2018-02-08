package com.example.alan.resume.delegate.pro.item;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.resume.R;
import com.example.alan.resume.entity.ProInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/2/6
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class ProItemAdapter extends RecyclerView.Adapter<ProItemAdapter.MyViewHolder>{

    private List<ProInfo> proInfos;
    private LayoutInflater inflater;

    public ProItemAdapter(List<ProInfo> infos, Context context){
        this.proInfos = infos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_multiple_pro_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProInfo info = proInfos.get(position);
        Log.e("tang",info.toString()+"=============");
        holder.mTextProTime.setText(info.getMStartTime()+"-"+info.getMEndTime());
        holder.mTextProTitle.setText(info.getMTitle());

    }


    @Override
    public int getItemCount() {
        return proInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView mTextProTime;
        AppCompatTextView mTextProTitle;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTextProTime = itemView.findViewById(R.id.tv_pro_time);
            mTextProTitle = itemView.findViewById(R.id.tv_pro_title);

        }
    }
}
