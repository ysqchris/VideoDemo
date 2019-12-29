package com.yusq.videodemo.model.hangzhou.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yusq.videodemo.R;
import com.yusq.videodemo.bean.ShangHaiDetailBean;
import com.yusq.videodemo.model.shanghai.ShangHaiDetailActivity;

import java.util.List;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/14
 * <p>
 * 包 名：com.yusq.videodemo.model.shanghai.adapter
 * <p>
 * 类 名：BeHaviorAdapter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class BeHaviorAdapter extends RecyclerView.Adapter {

    private List<ShangHaiDetailBean.ResultBean> mDataList;
    private Context mContext ;

    public BeHaviorAdapter(Context pContext , List<ShangHaiDetailBean.ResultBean> pTArrayList) {
          mDataList = pTArrayList;
          mContext = pContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int viewType) {
            View item = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.shanghai_recycler_view_item, pViewGroup , false);
            return  new MyVerticalViewHolder(item);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {
         if(mDataList != null && pViewHolder != null){
             ShangHaiDetailBean.ResultBean itemBean = mDataList.get(pI);
             if(itemBean != null) {
                 if(pViewHolder instanceof MyVerticalViewHolder) {
                     ((MyVerticalViewHolder) pViewHolder).itemContentTv.setText(itemBean.content);
                     ((MyVerticalViewHolder)pViewHolder).itemContentTv.setTag(pI);
                 }
             }
         }
    }

    @Override
    public int getItemCount() {
        if(mDataList != null){
            return mDataList.size();
        }
        return 0;
    }


    class  MyVerticalViewHolder extends RecyclerView.ViewHolder{

        public TextView  itemContentTv;

        public MyVerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View pView) {
           if(pView != null){
               itemContentTv = pView.findViewById(R.id.shanghai_recycler_view_item_content);
               itemContentTv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(mContext , ShangHaiDetailActivity.class);
                       mContext.startActivity(intent);
                   }
               });
           }
        }
    }
}
