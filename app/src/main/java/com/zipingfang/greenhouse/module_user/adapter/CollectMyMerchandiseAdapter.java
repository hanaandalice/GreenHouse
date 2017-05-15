package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.CollectMyMerchandiseModel;

/**
 * Created by Administrator on 2017/5/11.
 */

public class CollectMyMerchandiseAdapter extends BaseQuickAdapter<CollectMyMerchandiseModel,BaseViewHolder> {
    private Context context;

    public CollectMyMerchandiseAdapter(Context context) {
        super(R.layout.item_collect_my_merchandise);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectMyMerchandiseModel item, int position) {

    }

}
