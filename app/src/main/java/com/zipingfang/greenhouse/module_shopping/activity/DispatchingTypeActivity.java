package com.zipingfang.greenhouse.module_shopping.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.adapter.DispatchingTypeAdapter;
import com.zipingfang.greenhouse.module_shopping.model.DispatchingTypeModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/9.
 */

public class DispatchingTypeActivity extends TitleBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_dispatching_type;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("选择支付配送方式");
        setRightButtonText("确定");
        setRightButtonTextColor(getResources().getColor(R.color.redColor_f2));
        unbinder = ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        DispatchingTypeAdapter dispatchingTypeAdapter =new DispatchingTypeAdapter(this);
        recyclerView.setAdapter(dispatchingTypeAdapter);
        List<DispatchingTypeModel>  dispatchingTypeModelList =new ArrayList<>();
        dispatchingTypeModelList.add(new DispatchingTypeModel());
        dispatchingTypeModelList.add(new DispatchingTypeModel());
        dispatchingTypeAdapter.addData(dispatchingTypeModelList);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
