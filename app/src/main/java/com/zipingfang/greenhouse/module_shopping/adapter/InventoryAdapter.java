package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.InventoryChildModel;
import com.zipingfang.greenhouse.module_shopping.model.InventoryModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class InventoryAdapter extends BaseQuickAdapter<InventoryModel,BaseViewHolder> {

    private Context context;
    public InventoryAdapter(Context context) {
        super(R.layout.item_inventory);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InventoryModel item, int position) {
        helper.<RecyclerView>getView(R.id.recyclerView_child).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        helper.<RecyclerView>getView(R.id.recyclerView_child).addItemDecoration(new MyRecyclerDetorration(context, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        InventoryChildAdapter inventoryChildAdapter =new InventoryChildAdapter();
        helper.<RecyclerView>getView(R.id.recyclerView_child).setAdapter(inventoryChildAdapter);
        List<InventoryChildModel> inventoryChildModelList =new ArrayList<>();
        inventoryChildModelList.add(new InventoryChildModel());
        inventoryChildModelList.add(new InventoryChildModel());
        inventoryChildAdapter.addData(inventoryChildModelList);
    }

    class InventoryChildAdapter extends BaseQuickAdapter<InventoryChildModel,BaseViewHolder>{
        public InventoryChildAdapter(){
            super(R.layout.item_inventory_child);
        }
        @Override
        protected void convert(BaseViewHolder helper, InventoryChildModel item, int position) {

        }
    }
}
