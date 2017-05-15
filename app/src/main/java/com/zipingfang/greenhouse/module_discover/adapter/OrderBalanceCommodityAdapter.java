package com.zipingfang.greenhouse.module_discover.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.model.DiscoverModel;
import com.zipingfang.greenhouse.module_discover.model.OrderBalanceCommodityModel;
import com.zipingfang.greenhouse.module_shopping.adapter.InventoryAdapter;
import com.zipingfang.greenhouse.module_shopping.model.InventoryChildModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class OrderBalanceCommodityAdapter extends BaseQuickAdapter<OrderBalanceCommodityModel,BaseViewHolder> {
    private Context context;
    public OrderBalanceCommodityAdapter(Context context) {
        super(R.layout.item_order_balance_commodity);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBalanceCommodityModel item, int position) {
        if (position==0) {
            helper.setVisible(R.id.layout_single,true);
            helper.setVisible(R.id.layout_multi,false);
        }else{
            helper.setVisible(R.id.layout_single,false);
            helper.setVisible(R.id.layout_multi,true);
        }
        helper.<RecyclerView>getView(R.id.recyclerView_child).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        OrderBalanceCommodityChildAdapter inventoryChildAdapter =new OrderBalanceCommodityChildAdapter();
        helper.<RecyclerView>getView(R.id.recyclerView_child).setAdapter(inventoryChildAdapter);
        List<OrderBalanceCommodityModel.OrderBalanceCommodityChildModel> orderBalanceCommodityChildModelList =new ArrayList<>();
        orderBalanceCommodityChildModelList.add(new OrderBalanceCommodityModel.OrderBalanceCommodityChildModel());
        orderBalanceCommodityChildModelList.add(new OrderBalanceCommodityModel.OrderBalanceCommodityChildModel());
        orderBalanceCommodityChildModelList.add(new OrderBalanceCommodityModel.OrderBalanceCommodityChildModel());
        inventoryChildAdapter.addData(orderBalanceCommodityChildModelList);
    }

    class OrderBalanceCommodityChildAdapter extends BaseQuickAdapter<OrderBalanceCommodityModel.OrderBalanceCommodityChildModel,BaseViewHolder>{

        public OrderBalanceCommodityChildAdapter(){
            super(R.layout.item_order_balance_commodity_child);
        }
        @Override
        protected void convert(BaseViewHolder helper, OrderBalanceCommodityModel.OrderBalanceCommodityChildModel item, int position) {

        }
    }
}
