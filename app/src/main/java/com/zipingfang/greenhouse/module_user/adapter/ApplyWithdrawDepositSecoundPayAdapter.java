package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;
import android.view.View;

import com.xilada.xldutils.adapter.ListAdapter;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.ApplyWithdrawDepositSecoundPayModel;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 */

public class ApplyWithdrawDepositSecoundPayAdapter extends ListAdapter<ApplyWithdrawDepositSecoundPayModel> {
    private int selectPosition=-1;
    /**
     * 列表适配器
     *
     * @param ctx      上下文
     * @param list     数据list
     */
    public ApplyWithdrawDepositSecoundPayAdapter(Context ctx, List<ApplyWithdrawDepositSecoundPayModel> list) {
        super(ctx, list, R.layout.item_apply_withdraw_deposit_secound_pay);
    }

    @Override
    protected void onBind(final int position, ApplyWithdrawDepositSecoundPayModel applyWithdrawDepositSecoundPayModel, ViewHolder holder) {
        if (selectPosition==position) {
            holder.setVisibility(R.id.iv_selected, View.VISIBLE);
        }else{
            holder.setVisibility(R.id.iv_selected, View.GONE);
        }
        holder.setOnClickListener(R.id.layout_click, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition =position;
                notifyDataSetChanged();
            }
        });
    }
    public int getSelectPosition() {
        return selectPosition;
    }
}
