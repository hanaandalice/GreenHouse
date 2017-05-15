package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.InventoryChildModel;
import com.zipingfang.greenhouse.module_shopping.model.InventoryModel;
import com.zipingfang.greenhouse.module_shopping.model.OrderBalanceMoneyModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class OrderBalanceMoneyAdapter extends BaseQuickAdapter<OrderBalanceMoneyModel,BaseViewHolder> {

    private Context context;
    public OrderBalanceMoneyAdapter(Context context) {
        super(R.layout.item_order_balance_money);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBalanceMoneyModel item, int position) {

    }

}

