package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.CollectMyMerchandiseAdapter;
import com.zipingfang.greenhouse.module_user.dialog.RushToPurchaseNoticeDialog;
import com.zipingfang.greenhouse.module_user.model.CollectMyMerchandiseModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CollectMyMerchandiseFragment  extends BaseLazyFragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private TextView tv_all,
            tv_notice,
            tv_remind,
            tv_arrival_of_goods;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_collect_merchandise;
    }

    @Override
    protected void onFirstVisibleToUser() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView =findViewById(R.id.recyclerView);
        tv_all =findViewById(R.id.tv_all);
        tv_notice =findViewById(R.id.tv_notice);
        tv_remind =findViewById(R.id.tv_remind);
        tv_arrival_of_goods =findViewById(R.id.tv_arrival_of_goods);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new MyRecyclerDetorration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        CollectMyMerchandiseAdapter merchandiseAdapter =new CollectMyMerchandiseAdapter(getActivity());
        recyclerView.setAdapter(merchandiseAdapter);
        List<CollectMyMerchandiseModel> merchandiseModelList =new ArrayList<>();
        merchandiseModelList.add(new CollectMyMerchandiseModel());
        merchandiseModelList.add(new CollectMyMerchandiseModel());
        merchandiseAdapter.addData(merchandiseModelList);
        tv_all.setOnClickListener(this);
        tv_notice.setOnClickListener(this);
        tv_remind.setOnClickListener(this);
        tv_arrival_of_goods.setOnClickListener(this);
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
                tv_notice.setTextColor(getResources().getColor(R.color.text_color));
                tv_remind.setTextColor(getResources().getColor(R.color.text_color));
                tv_arrival_of_goods.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_notice:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_notice.setTextColor(getResources().getColor(R.color.bg_green));
                tv_remind.setTextColor(getResources().getColor(R.color.text_color));
                tv_arrival_of_goods.setTextColor(getResources().getColor(R.color.text_color));
                RushToPurchaseNoticeDialog toPurchaseNoticeDialog =new RushToPurchaseNoticeDialog(getActivity());
                
                break;
            case R.id.tv_remind:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_notice.setTextColor(getResources().getColor(R.color.text_color));
                tv_remind.setTextColor(getResources().getColor(R.color.bg_green));
                tv_arrival_of_goods.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_arrival_of_goods:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_notice.setTextColor(getResources().getColor(R.color.text_color));
                tv_remind.setTextColor(getResources().getColor(R.color.text_color));
                tv_arrival_of_goods.setTextColor(getResources().getColor(R.color.bg_green));
                break;
        }
    }
}
