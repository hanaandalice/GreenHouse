package com.zipingfang.greenhouse.module_shopping.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.adapter.InventoryAdapter;
import com.zipingfang.greenhouse.module_shopping.model.InventoryModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 商品清单
 * Created by Administrator on 2017/5/8.
 */

public class InventoryActivity extends TitleBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_inventory;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("商品清单");
        setRightButtonText("共6件");
        unbinder = ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        InventoryAdapter inventoryAdapter =new InventoryAdapter(this);
        recyclerView.setAdapter(inventoryAdapter);
        List<InventoryModel> inventoryModelList =new ArrayList<>();
        inventoryModelList.add(new InventoryModel());
        inventoryModelList.add(new InventoryModel());
        inventoryAdapter.addData(inventoryModelList);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
