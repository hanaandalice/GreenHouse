package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.model.CommodityRetailModel;
import com.zipingfang.greenhouse.module_home.model.CommodityWholesaleModel;

/**
 * Created by Administrator on 2017/5/2.
 */

public class CommodityRetailAdapter extends BaseQuickAdapter<CommodityRetailModel,BaseViewHolder> {
    private Context context;
    public  CommodityRetailAdapter(int layoutResId, Context context){
        super(layoutResId);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, CommodityRetailModel item, int position) {

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
