package com.yusq.videodemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yusq.videodemo.R;
import com.yusq.videodemo.bean.ShangHaiBean;
import com.yusq.videodemo.model.shanghai.ShangHaiDetailActivity;

import java.util.List;

/**
 * 项目名：VideoDemo
 * <p>
 * 时 间：2019/11/14
 * <p>
 * 包 名：com.yusq.videodemo.adapter
 * <p>
 * 类 名：ShangHaiAdapter
 * <p>
 * 作 者：Yusq
 * <p>
 * 简 述：
 */
public class ShangHaiAdapter  extends RecyclerView.Adapter {

    private List<ShangHaiBean> mDataList;
    private Context mContext ;
    private RecyclerView.RecycledViewPool recycledViewPool;
    public ShangHaiAdapter(Context pContext , List<ShangHaiBean> pTArrayList) {
          mDataList = pTArrayList;
          mContext = pContext;
          recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int viewType) {
        if(viewType == ShangHaiBean.IShangHaiItemType.VERTICAL) {
            View item = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.shanghai_recycler_view_item, pViewGroup , false);
           // item.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
            return  new MyVerticalViewHolder(item);
        }else if (viewType == ShangHaiBean.IShangHaiItemType.HORIZANTAL){
            View item = LayoutInflater.from(pViewGroup.getContext()).inflate(R.layout.shanghai_recycler_view_horizantal_layout, null);
            return new MyHorizatalViewHolder(item);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder pViewHolder, int pI) {
         if(mDataList != null && pViewHolder != null){
             ShangHaiBean itemBean = mDataList.get(pI);
             if(itemBean != null) {
                 if(pViewHolder instanceof MyVerticalViewHolder) {
                     ((MyVerticalViewHolder) pViewHolder).itemContentTv.setText(itemBean.getDec());
                     ((MyVerticalViewHolder)pViewHolder).itemContentTv.setTag(pI);
                 }else if(pViewHolder instanceof  MyHorizatalViewHolder){
                     MyHorizatalViewHolder horizatalViewHolder = ((MyHorizatalViewHolder) pViewHolder);
                     horizatalViewHolder.itemRecycleView.setAdapter(new ShangHaiAdapter(mContext , itemBean.gethListData()));
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

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemType();
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

    class  MyHorizatalViewHolder extends RecyclerView.ViewHolder{

        public RecyclerView itemRecycleView;

        public MyHorizatalViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View pView) {
            if(pView != null){
                itemRecycleView = pView.findViewById(R.id.shanghai_horizantal_recycle_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext , LinearLayout.HORIZONTAL , false);
                //复用其他列表的回收的item
                linearLayoutManager.setRecycleChildrenOnDetach(true);
                itemRecycleView.setLayoutManager(linearLayoutManager);
                itemRecycleView.setRecycledViewPool(recycledViewPool);
            }
        }
    }

}
