package com.zipingfang.greenhouse.module_shopping.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.adapter.OrderBalanceCommodityAdapter;
import com.zipingfang.greenhouse.module_discover.model.OrderBalanceCommodityModel;
import com.zipingfang.greenhouse.module_shopping.adapter.GreenCardOrderBalanceCommodityAdapter;
import com.zipingfang.greenhouse.module_shopping.adapter.GreenCardOrderBalanceMoneyAdapter;
import com.zipingfang.greenhouse.module_shopping.adapter.OrderBalanceMoneyAdapter;
import com.zipingfang.greenhouse.module_shopping.model.GreenCardOrderBalanceCommodityModel;
import com.zipingfang.greenhouse.module_shopping.model.GreenCardOrderBalanceMoneyModel;
import com.zipingfang.greenhouse.module_shopping.model.OrderBalanceMoneyModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 绿卡结算
 * Created by Administrator on 2017/5/8.
 */

public class GreenCardOrderBalanceActivity extends TitleBarActivity implements View.OnClickListener{

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.recyclerView_money)
    RecyclerView recyclerView_money;
    @BindView(R.id.recyclerView_commodity)
    RecyclerView recyclerView_commodity;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_green_card_oreder_balance;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        tv_title.setText("订单结算");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView_commodity.setLayoutManager(layoutManager);
        recyclerView_commodity.addItemDecoration(new MyRecyclerDetorration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        GreenCardOrderBalanceCommodityAdapter commodityAdapter =new GreenCardOrderBalanceCommodityAdapter(this);
        recyclerView_commodity.setAdapter(commodityAdapter);
        List<GreenCardOrderBalanceCommodityModel> orderBalanceCommodityModelList =new ArrayList<>();
        orderBalanceCommodityModelList.add(new GreenCardOrderBalanceCommodityModel());
        commodityAdapter.addData(orderBalanceCommodityModelList);

        LinearLayoutManager layoutManagerMoney = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManagerMoney.setSmoothScrollbarEnabled(true);
        layoutManagerMoney.setAutoMeasureEnabled(true);
        recyclerView_money.setLayoutManager(layoutManagerMoney);
        recyclerView_money.addItemDecoration(new MyRecyclerDetorration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        GreenCardOrderBalanceMoneyAdapter cardOrderBalanceMoneyAdapter =new GreenCardOrderBalanceMoneyAdapter(this);
        recyclerView_money.setAdapter(cardOrderBalanceMoneyAdapter);
        List<GreenCardOrderBalanceMoneyModel> orderBalanceMoneyModelList =new ArrayList<>();
        orderBalanceMoneyModelList.add(new GreenCardOrderBalanceMoneyModel());
        cardOrderBalanceMoneyAdapter.addData(orderBalanceMoneyModelList);

        iv_back.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
