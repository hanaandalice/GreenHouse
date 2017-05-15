package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.xilada.xldutils.adapter.BaseRecyclerAdapter;
import com.xilada.xldutils.adapter.util.ViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.model.MainAllModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */

public class MainAllAdapter extends BaseRecyclerAdapter<MainAllModel> {
    public MainAllAdapter(List<MainAllModel> mData, Context context) {
        super(mData, R.layout.item_main_all, context);
    }

    @Override
    public void onBind(int position, MainAllModel mainAllModel, ViewHolder holder) {
        holder.setText(R.id.tv_classify_type,mainAllModel.getTest_name());
        holder.<ImageView>bind(R.id.iv_classify_icon).setImageResource(mainAllModel.getTest_iv());
    }
}
