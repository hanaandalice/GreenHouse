package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.MyEvaluationModel;
import com.zipingfang.greenhouse.module_user.model.MyFootprintModel;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyFootprintAdapter extends BaseQuickAdapter<MyFootprintModel,BaseViewHolder> {
    private Context context;

    public MyFootprintAdapter(Context context) {
        super(R.layout.item_my_footprint);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFootprintModel item, int position) {

    }
}
