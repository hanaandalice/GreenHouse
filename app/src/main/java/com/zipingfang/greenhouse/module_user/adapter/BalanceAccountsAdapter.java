package com.zipingfang.greenhouse.module_user.adapter;

import android.content.Context;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.BalanceAccountsModel;
import com.zipingfang.greenhouse.module_user.model.CollectMyMerchandiseModel;

/**
 * Created by Administrator on 2017/5/12.
 */

public class BalanceAccountsAdapter extends BaseQuickAdapter<BalanceAccountsModel,BaseViewHolder> {
    private Context context;

    public BalanceAccountsAdapter(Context context) {
        super(R.layout.item_balance_accounts);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BalanceAccountsModel item, int position) {

    }
}
