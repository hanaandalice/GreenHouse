package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.model.CommodityWholesaleModel;

/**
 * Created by Administrator on 2017/5/2.
 */

public class CommodityWhlesaleAdapter extends BaseQuickAdapter<CommodityWholesaleModel,BaseViewHolder> {
    private Context context;
    public  CommodityWhlesaleAdapter(int layoutResId, Context context){
        super(layoutResId);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, CommodityWholesaleModel item, int position) {
        for (int i = 0; i < helper.<RadioGroup>getView(R.id.radio_layout).getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) helper.<RadioGroup>getView(R.id.radio_layout).getChildAt(i);
            radioButton.setButtonDrawable(R.drawable.checked_green_r);
        }
        helper.getView(R.id.iv_shopping_cat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=shoppingClickListener) {
                    shoppingClickListener.onClick();
                }
            }
        });
    }

    public interface ShoppingClickListener{
        void onClick();
    }
    private ShoppingClickListener shoppingClickListener;
    public void setShoppingClickListener(ShoppingClickListener shoppingClickListener){
        this.shoppingClickListener =shoppingClickListener;
    }
}
