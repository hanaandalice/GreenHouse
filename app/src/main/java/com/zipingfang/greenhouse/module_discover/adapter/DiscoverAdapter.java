package com.zipingfang.greenhouse.module_discover.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.model.DiscoverModel;

/**
 * Created by Administrator on 2017/5/5.
 */

public class DiscoverAdapter extends BaseQuickAdapter<DiscoverModel,BaseViewHolder> {
    private Context context;

    public DiscoverAdapter(Context context) {
        super(R.layout.item_discover);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverModel item, int position) {

    }
}