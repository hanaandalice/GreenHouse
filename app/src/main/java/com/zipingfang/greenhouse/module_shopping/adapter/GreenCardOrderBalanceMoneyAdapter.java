package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.GreenCardOrderBalanceMoneyModel;
import com.zipingfang.greenhouse.module_shopping.model.OrderBalanceMoneyModel;

/**
 * Created by Administrator on 2017/5/8.
 */

public class GreenCardOrderBalanceMoneyAdapter extends BaseQuickAdapter<GreenCardOrderBalanceMoneyModel,BaseViewHolder> {

    private Context context;
    public GreenCardOrderBalanceMoneyAdapter(Context context) {
        super(R.layout.item_order_balance_money);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GreenCardOrderBalanceMoneyModel item, int position) {

    }

}
