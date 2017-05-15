package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.view.View;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.adapter.CommodityRetailAdapter;
import com.zipingfang.greenhouse.module_home.model.CommodityRetailModel;

/**
 * Created by Administrator on 2017/5/10.
 */

public class FavorableCommodityAdapter extends BaseQuickAdapter<CommodityRetailModel,BaseViewHolder> {
    private Context context;
    public  FavorableCommodityAdapter(Context context){
        super(R.layout.item_favorable_commodity);
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
    private CommodityRetailAdapter.ShoppingClickListener shoppingClickListener;
    public void setShoppingClickListener(CommodityRetailAdapter.ShoppingClickListener shoppingClickListener){
        this.shoppingClickListener =shoppingClickListener;
    }
}
