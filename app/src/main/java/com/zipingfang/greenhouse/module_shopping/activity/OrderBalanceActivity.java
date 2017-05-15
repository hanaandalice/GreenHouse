package com.zipingfang.greenhouse.module_shopping.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.adapter.OrderBalanceCommodityAdapter;
import com.zipingfang.greenhouse.module_discover.model.OrderBalanceCommodityModel;
import com.zipingfang.greenhouse.module_shopping.adapter.OrderBalanceBargainAdapter;
import com.zipingfang.greenhouse.module_shopping.adapter.OrderBalanceMoneyAdapter;
import com.zipingfang.greenhouse.module_shopping.model.OrderBalanceBargainModel;
import com.zipingfang.greenhouse.module_shopping.model.OrderBalanceMoneyModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/8.
 */

public class OrderBalanceActivity extends TitleBarActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_go_to_generate_order)
    TextView tv_go_to_generate_order;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.recyclerView_commodity)
    RecyclerView recyclerView_commodity;
    @BindView(R.id.recyclerView_money)
    RecyclerView recyclerView_money;
    @BindView(R.id.recyclerView_bargain)
    RecyclerView recyclerView_bargain;
    @BindView(R.id.layout_go_to_invoice)
    LinearLayout layout_go_to_invoice;
    @BindView(R.id.layout_dispatching_type)
    LinearLayout layout_dispatching_type;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_order_balance;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        tv_title.setText("订单结算");
//        iv_back.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView_commodity.setLayoutManager(layoutManager);
        recyclerView_commodity.addItemDecoration(new MyRecyclerDetorration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        OrderBalanceCommodityAdapter commodityAdapter =new OrderBalanceCommodityAdapter(this);
        recyclerView_commodity.setAdapter(commodityAdapter);
        List<OrderBalanceCommodityModel> orderBalanceCommodityModelList =new ArrayList<>();
        orderBalanceCommodityModelList.add(new OrderBalanceCommodityModel());
        orderBalanceCommodityModelList.add(new OrderBalanceCommodityModel());
        commodityAdapter.addData(orderBalanceCommodityModelList);

        LinearLayoutManager layoutManagerBargain = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManagerBargain.setSmoothScrollbarEnabled(true);
        layoutManagerBargain.setAutoMeasureEnabled(true);
        recyclerView_bargain.setLayoutManager(layoutManagerBargain);
        OrderBalanceBargainAdapter orderBalanceBargainAdapter =new OrderBalanceBargainAdapter(this);
        recyclerView_bargain.setAdapter(orderBalanceBargainAdapter);
        List<OrderBalanceBargainModel> orderBalanceBargainModelList =new ArrayList<>();
        orderBalanceBargainModelList.add(new OrderBalanceBargainModel());
        orderBalanceBargainModelList.add(new OrderBalanceBargainModel());
        orderBalanceBargainAdapter.addData(orderBalanceBargainModelList);

        LinearLayoutManager layoutManagerMoney = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManagerMoney.setSmoothScrollbarEnabled(true);
        layoutManagerMoney.setAutoMeasureEnabled(true);
        recyclerView_money.setLayoutManager(layoutManagerMoney);
        recyclerView_money.addItemDecoration(new MyRecyclerDetorration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        OrderBalanceMoneyAdapter orderBalanceMoneyAdapter =new OrderBalanceMoneyAdapter(this);
        recyclerView_money.setAdapter(orderBalanceMoneyAdapter);
        List<OrderBalanceMoneyModel> orderBalanceMoneyModelList =new ArrayList<>();
        orderBalanceMoneyModelList.add(new OrderBalanceMoneyModel());
        orderBalanceMoneyModelList.add(new OrderBalanceMoneyModel());
        orderBalanceMoneyAdapter.addData(orderBalanceMoneyModelList);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
    @OnClick({R.id.tv_go_to_generate_order,R.id.iv_back,R.id.layout_go_to_invoice
            ,R.id.layout_dispatching_type})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_go_to_generate_order:
                goActivity(OrderPaySelectedActivity.class);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.layout_go_to_invoice:
                goActivity(OrderInvoiceTypeActivity.class);
                break;
            case R.id.layout_dispatching_type:
                goActivity(DispatchingTypeActivity.class);
                break;

        }
    }

}
