package com.zipingfang.greenhouse.module_classify.adapter;


import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_classify.model.ClassifyTypeModel;

/**
 * Created by admin on 2017/3/22.
 */

public class TypeOfGoodsNameAdapter extends BaseQuickAdapter<ClassifyTypeModel,BaseViewHolder> {
    private Context context;
    public TypeOfGoodsNameAdapter(Context context) {
        super(R.layout.item_type_of_goods_name_view);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, ClassifyTypeModel item ,int position) {
        helper.setText(R.id.goods_type_name,item.getType());
        if (item.isChecked()) {
            helper.setTextColor(R.id.goods_type_name,context.getResources().getColor(R.color.bg_green));
            helper.getView(R.id.goods_type_name).setBackgroundColor(0);
        }else{
            helper.setTextColor(R.id.goods_type_name,context.getResources().getColor(R.color.text_color));
            helper.getView(R.id.goods_type_name).setBackgroundColor(context.getResources().getColor(R.color.white));
        }

    }
}
