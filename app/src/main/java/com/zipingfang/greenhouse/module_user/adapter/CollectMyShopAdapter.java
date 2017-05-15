package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.CollectMyShopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CollectMyShopAdapter extends BaseQuickAdapter<CollectMyShopModel,BaseViewHolder> {
    private Context context;

    public CollectMyShopAdapter(Context context) {
        super(R.layout.item_collect_my_shop);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectMyShopModel item, int position) {

        helper.<RecyclerView>getView(R.id.recyclerView_preferential).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        CollectMyShopPreferentialAdapter collectMyShopPreferentialAdapter =new CollectMyShopPreferentialAdapter(context);
        helper.<RecyclerView>getView(R.id.recyclerView_preferential).setAdapter(collectMyShopPreferentialAdapter);
        List<CollectMyShopModel.CollectMyShopPreferentialModel> collectMyShopPreferentialModelList =new ArrayList<>();
        collectMyShopPreferentialModelList.add(new CollectMyShopModel.CollectMyShopPreferentialModel());
        collectMyShopPreferentialModelList.add(new CollectMyShopModel.CollectMyShopPreferentialModel());
        collectMyShopPreferentialAdapter.addData(collectMyShopPreferentialModelList);

        helper.<RecyclerView>getView(R.id.recyclerView_commodity).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        CollectMyShopCommodityAdapter myShopCommodityAdapter =new CollectMyShopCommodityAdapter(context);
        helper.<RecyclerView>getView(R.id.recyclerView_commodity).setAdapter(myShopCommodityAdapter);
        List<CollectMyShopModel.CollectMyShopCommodityModel> shopCommodityModelList =new ArrayList<>();
        shopCommodityModelList.add(new CollectMyShopModel.CollectMyShopCommodityModel());
        shopCommodityModelList.add(new CollectMyShopModel.CollectMyShopCommodityModel());
        shopCommodityModelList.add(new CollectMyShopModel.CollectMyShopCommodityModel());
        myShopCommodityAdapter.addData(shopCommodityModelList);


    }
    class CollectMyShopPreferentialAdapter extends BaseQuickAdapter<CollectMyShopModel.CollectMyShopPreferentialModel,BaseViewHolder> {
        public CollectMyShopPreferentialAdapter(Context context) {
            super(R.layout.item_collect_my_shop_preferential);
        }
        @Override
        protected void convert(BaseViewHolder helper, CollectMyShopModel.CollectMyShopPreferentialModel item, int position) {

        }
    }
    class CollectMyShopCommodityAdapter extends BaseQuickAdapter<CollectMyShopModel.CollectMyShopCommodityModel,BaseViewHolder> {
        public CollectMyShopCommodityAdapter(Context context) {
            super(R.layout.item_collect_my_shop_commodity);
        }
        @Override
        protected void convert(BaseViewHolder helper, CollectMyShopModel.CollectMyShopCommodityModel item, int position) {

        }
    }
}
