package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.CollectMyShopModel;
import com.zipingfang.greenhouse.module_user.model.MyEvaluationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyEvaluationAdapter extends BaseQuickAdapter<MyEvaluationModel,BaseViewHolder> {
    private Context context;

    public MyEvaluationAdapter(Context context) {
        super(R.layout.item_my_evaluation);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyEvaluationModel item, int position) {

    }
}
