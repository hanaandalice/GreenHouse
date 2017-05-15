package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xilada.xldutils.adapter.ListAdapter;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.OrderInvoiceGridModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class OrderInvoiceGridAdapter extends ListAdapter<OrderInvoiceGridModel> {
    /**
     * 列表适配器
     *
     * @param ctx      上下文
     * @param list     数据list
     */
    private int selectPosition=-1;
    private Context context;
    public OrderInvoiceGridAdapter(Context ctx, List<OrderInvoiceGridModel> list,int selectPosition,int layout) {
        super(ctx, list,layout );
        this.context =ctx;
        this.selectPosition =selectPosition;
    }

    @Override
    protected void onBind(final int position, OrderInvoiceGridModel orderInvoiceGridModel, ViewHolder holder) {
        holder.setText(R.id.tv_tag,orderInvoiceGridModel.getContents());
        if (position ==selectPosition){
            holder.setVisibility(R.id.iv_selected_true,View.VISIBLE);
            holder.<TextView>bind(R.id.tv_tag).setBackground(context.getDrawable(R.drawable.rect_transparency_bg_green_1dp_line));
        }else{
            holder.setVisibility(R.id.iv_selected_true,View.GONE);
            holder.<TextView>bind(R.id.tv_tag).setBackground(context.getDrawable(R.drawable.rect_white_bg_1dp_line));
        }
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }
}
