package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.DispatchingTypeModel;
import com.zipingfang.greenhouse.view.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class DispatchingTypeAdapter extends BaseQuickAdapter<DispatchingTypeModel,BaseViewHolder> {

    private Context context;
    public DispatchingTypeAdapter(Context context) {
        super(R.layout.item_dispatching_type);
        this.context = context;
    }
    @Override
    protected void convert(final BaseViewHolder helper, DispatchingTypeModel item, int position) {
        helper.getView(R.id.tv_online_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setTextColor(R.id.tv_online_pay,context.getResources().getColor(R.color.bg_green));
                helper.setTextColor(R.id.tv_line_pay,context.getResources().getColor(R.color.text_color));
                helper.getView(R.id.tv_online_pay).setBackground(context.getResources().getDrawable(R.drawable.divider_dispatching_true));
                helper.getView(R.id.tv_line_pay).setBackground(context.getResources().getDrawable(R.drawable.divider_dispatching_false));
            }
        });
        helper.getView(R.id.tv_line_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setTextColor(R.id.tv_line_pay,context.getResources().getColor(R.color.bg_green));
                helper.setTextColor(R.id.tv_online_pay,context.getResources().getColor(R.color.text_color));
                helper.getView(R.id.tv_online_pay).setBackground(context.getResources().getDrawable(R.drawable.divider_dispatching_false));
                helper.getView(R.id.tv_line_pay).setBackground(context.getResources().getDrawable(R.drawable.divider_dispatching_true));
            }
        });
        helper.<RecyclerView>getView(R.id.recyclerView_commodity).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        DispatchingTypeCommodityAdapter commodityAdapter =new DispatchingTypeCommodityAdapter();
        helper.<RecyclerView>getView(R.id.recyclerView_commodity).setAdapter(commodityAdapter);
        List<DispatchingTypeModel.DispatchingTypeCommodity> dispatchingTypeCommodityList =new ArrayList<>();
        dispatchingTypeCommodityList.add(new DispatchingTypeModel.DispatchingTypeCommodity());
        dispatchingTypeCommodityList.add(new DispatchingTypeModel.DispatchingTypeCommodity());
        dispatchingTypeCommodityList.add(new DispatchingTypeModel.DispatchingTypeCommodity());
        dispatchingTypeCommodityList.add(new DispatchingTypeModel.DispatchingTypeCommodity());
        dispatchingTypeCommodityList.add(new DispatchingTypeModel.DispatchingTypeCommodity());
        commodityAdapter.addData(dispatchingTypeCommodityList);

        helper.<RecyclerView>getView(R.id.recyclerView_dispatching).setLayoutManager(new GridLayoutManager(context,3));
        helper.<RecyclerView>getView(R.id.recyclerView_dispatching).addItemDecoration(new GridSpacingItemDecoration(3,dip2px(context,20),false));
        final DispatchingTypeDispatchingAdapter dispatchingAdapter =new DispatchingTypeDispatchingAdapter();
        helper.<RecyclerView>getView(R.id.recyclerView_dispatching).setAdapter(dispatchingAdapter);
        List<DispatchingTypeModel.DispatchingTypedDispatching> typedDispatchingList =new ArrayList<>();
        typedDispatchingList.add(new DispatchingTypeModel.DispatchingTypedDispatching("自营配送"));
        typedDispatchingList.add(new DispatchingTypeModel.DispatchingTypedDispatching("物流自提"));
        typedDispatchingList.add(new DispatchingTypeModel.DispatchingTypedDispatching("上门自提"));
        dispatchingAdapter.addData(typedDispatchingList);
        dispatchingAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position==0){
                    helper.setVisible(R.id.tv_dispathchling_01,true);
                    helper.setVisible(R.id.layout_dispathchling_02,false);
                }else{
                    helper.setVisible(R.id.tv_dispathchling_01,false);
                    helper.setVisible(R.id.layout_dispathchling_02,true);
                }
                dispatchingAdapter.setSelectPosition(position);
            }
        });
    }

    class DispatchingTypeCommodityAdapter extends BaseQuickAdapter<DispatchingTypeModel.DispatchingTypeCommodity,BaseViewHolder>{
        public DispatchingTypeCommodityAdapter() {
            super(R.layout.item_img);

        }
        @Override
        protected void convert(BaseViewHolder helper, DispatchingTypeModel.DispatchingTypeCommodity item, int position) {

        }
    }
    class DispatchingTypeDispatchingAdapter extends BaseQuickAdapter<DispatchingTypeModel.DispatchingTypedDispatching,BaseViewHolder>{
        private int selectPosition=-1;
        public DispatchingTypeDispatchingAdapter() {
            super(R.layout.item_order_invoice_grid);

        }
        @Override
        protected void convert(BaseViewHolder helper, DispatchingTypeModel.DispatchingTypedDispatching item, int position) {
            helper.setText(R.id.tv_tag,item.getContents());

            if (position ==selectPosition){
                helper.setVisible(R.id.iv_selected_true,true);
                helper.<TextView>getView(R.id.tv_tag).setBackground(context.getDrawable(R.drawable.rect_transparency_bg_green_1dp_line));
            }else{
                helper.setVisible(R.id.iv_selected_true,false);
                helper.<TextView>getView(R.id.tv_tag).setBackground(context.getDrawable(R.drawable.rect_white_bg_1dp_line));
            }
        }

        public int getSelectPosition() {
            return selectPosition;
        }

        public void setSelectPosition(int selectPosition) {
            this.selectPosition = selectPosition;
            notifyDataSetChanged();
        }
    }
    public int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
}
