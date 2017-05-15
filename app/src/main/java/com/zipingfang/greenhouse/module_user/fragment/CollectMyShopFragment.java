package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.CollectMyShopAdapter;
import com.zipingfang.greenhouse.module_user.model.CollectMyShopModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CollectMyShopFragment extends BaseLazyFragment implements View.OnClickListener{
    private TextView tv_all,
            tv_sales_promotion,
            tv_first,
            tv_new_product;

    private RecyclerView recyclerView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_collect_shop;
    }

    @Override
    protected void onFirstVisibleToUser() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView =findViewById(R.id.recyclerView);
        tv_all =findViewById(R.id.tv_all);
        tv_sales_promotion =findViewById(R.id.tv_sales_promotion);
        tv_first =findViewById(R.id.tv_first);
        tv_new_product =findViewById(R.id.tv_new_product);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new MyRecyclerDetorration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        CollectMyShopAdapter merchandiseAdapter =new CollectMyShopAdapter(getActivity());
        recyclerView.setAdapter(merchandiseAdapter);
        List<CollectMyShopModel> collectMyShopModelList =new ArrayList<>();
        collectMyShopModelList.add(new CollectMyShopModel());
        collectMyShopModelList.add(new CollectMyShopModel());
        merchandiseAdapter.addData(collectMyShopModelList);

        tv_all.setOnClickListener(this);
        tv_sales_promotion.setOnClickListener(this);
        tv_first.setOnClickListener(this);
        tv_new_product.setOnClickListener(this);
    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                tv_all.setTextColor(getResources().getColor(R.color.bg_green));
                tv_sales_promotion.setTextColor(getResources().getColor(R.color.text_color));
                tv_first.setTextColor(getResources().getColor(R.color.text_color));
                tv_new_product.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_sales_promotion:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_sales_promotion.setTextColor(getResources().getColor(R.color.bg_green));
                tv_first.setTextColor(getResources().getColor(R.color.text_color));
                tv_new_product.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_first:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_sales_promotion.setTextColor(getResources().getColor(R.color.text_color));
                tv_first.setTextColor(getResources().getColor(R.color.bg_green));
                tv_new_product.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_new_product:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_sales_promotion.setTextColor(getResources().getColor(R.color.text_color));
                tv_first.setTextColor(getResources().getColor(R.color.text_color));
                tv_new_product.setTextColor(getResources().getColor(R.color.bg_green));
                break;
        }
    }
}
