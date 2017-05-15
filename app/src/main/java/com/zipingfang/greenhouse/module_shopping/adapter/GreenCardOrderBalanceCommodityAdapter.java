package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.view.View;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.GreenCardOrderBalanceCommodityModel;
import com.zipingfang.greenhouse.module_shopping.model.GreenCardOrderBalanceMoneyModel;

/**
 * Created by Administrator on 2017/5/9.
 */

public class GreenCardOrderBalanceCommodityAdapter extends BaseQuickAdapter<GreenCardOrderBalanceCommodityModel,BaseViewHolder> {

    private Context context;
    public GreenCardOrderBalanceCommodityAdapter(Context context) {
        super(R.layout.item_order_balance_commodity);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GreenCardOrderBalanceCommodityModel item, int position) {
        helper.setVisible(R.id.layout_single, true);
        helper.setVisible(R.id.layout_multi, false);
    }
}
