package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xilada.xldutils.view.flawtaglayout.OnInitSelectedPosition;
import com.zipingfang.greenhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class CommodityFlowTabAdapter extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private List<String> mDataList =new ArrayList<>();
    private int selectedPosition=-1;
    public CommodityFlowTabAdapter(Context context) {
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public String getItem(int position) {
        return mDataList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_commodity_flow_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        String t = getItem(position);
        if (selectedPosition==position) {
            textView.setBackground(mContext.getDrawable(R.mipmap.bg_commodity_selected_true));
        }else{
            textView.setBackground(mContext.getDrawable(R.mipmap.bg_commodity_selected_false));
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        if (t instanceof String) {
            textView.setText(t);
        }
        return view;
    }

    public void onlyAddAll(List<String> datas) {
        mDataList.clear();
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }
    public void clearAndAddAll(List<String> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }
    public List<String> getmDataList(){
        return this.mDataList;
    }
    @Override
    public boolean isSelectedPosition(int position) {
//        if (position % 2 == 0) {
//            return true;
//        }
        return false;
    }
}
