package com.zipingfang.greenhouse.module_classify.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_classify.model.ClassifyLogoModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyTypeModel;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ClassifyLogoAdapter extends BaseQuickAdapter<ClassifyLogoModel,BaseViewHolder> {

    private Context context;
    public ClassifyLogoAdapter(Context context) {
        super(R.layout.item_classify_logo);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyLogoModel item, int position) {

    }
}