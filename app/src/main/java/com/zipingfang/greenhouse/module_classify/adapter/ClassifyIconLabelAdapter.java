package com.zipingfang.greenhouse.module_classify.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_classify.model.ClassifyIconLabelModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyLogoModel;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ClassifyIconLabelAdapter  extends BaseQuickAdapter<ClassifyIconLabelModel,BaseViewHolder> {

    private Context context;
    public ClassifyIconLabelAdapter(Context context) {
        super(R.layout.item_classify_icon_label);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyIconLabelModel item, int position) {

    }
}
