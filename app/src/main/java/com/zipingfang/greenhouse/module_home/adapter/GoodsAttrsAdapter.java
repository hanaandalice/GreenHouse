package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.model.SaleAttributeVo;

import java.util.ArrayList;
import java.util.List;


/**
 * 子属性GridView的适配器
 */
public class GoodsAttrsAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeVo> data = new ArrayList<SaleAttributeVo>();

    public GoodsAttrsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(context, R.layout.item_goods_attrs, null);
            myView.attr = (TextView) v.findViewById(R.id.attr_name);
            myView.layout_img = (RelativeLayout) v.findViewById(R.id.layout_img);
            myView.iv_img = (ImageView) v.findViewById(R.id.iv_img);
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (data.get(position).getValue().startsWith("http")) {
            myView.layout_img.setVisibility(View.VISIBLE);
            myView.attr.setVisibility(View.GONE);
        }else{
            myView.attr.setVisibility(View.VISIBLE);
            myView.layout_img.setVisibility(View.GONE);
        }
        if (data.get(position).isChecked()) {
            myView.attr.setTextColor(context.getColor(R.color.bg_green));
            myView.attr.setBackground(context.getDrawable(R.drawable.radius_5dp_white_bg_green_1dp_line));
            myView.attr.setText("√ "+data.get(position).getValue());
            myView.layout_img.setBackgroundColor(context.getColor(R.color.bg_green));
        } else {
            myView.attr.setTextColor(context.getColor(R.color.grey_hint));
            myView.attr.setBackground(context.getDrawable(R.drawable.radius_5dp_grey2f_bg));
            myView.attr.setText(data.get(position).getValue());
            myView.layout_img.setBackgroundColor(context.getColor(R.color.white));
        }
        return v;
    }

    static class MyView {
        public TextView attr;
        public RelativeLayout layout_img;
        public ImageView iv_img;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(boolean isUnfold,
                                     final List<SaleAttributeVo> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            data.add(tempData.get(0));
            data.add(tempData.get(1));
            data.add(tempData.get(2));
        }
        notifyDataSetChanged();
    }

}
