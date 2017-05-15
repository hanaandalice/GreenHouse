package com.zipingfang.greenhouse.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.model.CommodityCommentModel;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CommodityCommentAdapter extends BaseQuickAdapter<CommodityCommentModel,BaseViewHolder> {
    private Context context;

    public CommodityCommentAdapter(Context context) {
        super(R.layout.item_commodity_comment);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommodityCommentModel item, int position) {

    }

    public interface ShoppingClickListener {
        void onClick();
    }
}
