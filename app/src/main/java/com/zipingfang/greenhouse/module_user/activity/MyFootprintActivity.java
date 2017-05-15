package com.zipingfang.greenhouse.module_user.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.MyFootprintAdapter;
import com.zipingfang.greenhouse.module_user.model.MyFootprintModel;
import com.zipingfang.greenhouse.module_user.utils.DividerItemDecoration;
import com.zipingfang.greenhouse.module_user.utils.HeaderRecyclerAndFooterWrapperAdapter;
import com.zipingfang.greenhouse.module_user.utils.ViewHolder;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/11.
 */

public class MyFootprintActivity extends TitleBarActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;

    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_my_footprint;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder= ButterKnife.bind(this);
        tv_title.setText("我的足迹");
        List<MyFootprintModel> footprintModelList =new ArrayList<>();
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("今天"));
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("今天"));
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("昨天"));
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("前天"));
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("更早"));
        footprintModelList.add(new MyFootprintModel().setBaseIndexTag("更早"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        MyFootprintAdapter myFootprintAdapter =new MyFootprintAdapter(this);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(myFootprintAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {

            }
        };
        recyclerView.setAdapter(mHeaderAdapter);
        recyclerView.addItemDecoration(new SuspensionDecoration(this, footprintModelList).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        recyclerView.addItemDecoration(new DividerItemDecoration(MyFootprintActivity.this, DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.addItemDecoration(new MyRecyclerDetorration(this, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        myFootprintAdapter.addData(footprintModelList);

    }
    @OnClick({R.id.iv_back})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
