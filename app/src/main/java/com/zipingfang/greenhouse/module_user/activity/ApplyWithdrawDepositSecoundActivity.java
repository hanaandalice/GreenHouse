package com.zipingfang.greenhouse.module_user.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.view.ListForScoroller;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.ApplyWithdrawDepositSecoundBankAdapter;
import com.zipingfang.greenhouse.module_user.adapter.ApplyWithdrawDepositSecoundPayAdapter;
import com.zipingfang.greenhouse.module_user.model.ApplyWithdrawDepositSecoundBankModel;
import com.zipingfang.greenhouse.module_user.model.ApplyWithdrawDepositSecoundPayModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/13.
 */

public class ApplyWithdrawDepositSecoundActivity extends TitleBarActivity {
    @BindView(R.id.listView_bank)
    ListForScoroller listView_bank;
    @BindView(R.id.listView_pay)
    ListForScoroller listView_pay;
    @BindView(R.id.iv_add_bank)
    ImageView iv_add_bank;
    private Unbinder unbinder;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_apply_withdraw_deposit_secound;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("提现申请");
        setRightButton("", getResources().getDrawable(R.mipmap.icon_more), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        unbinder = ButterKnife.bind(this);
        List<ApplyWithdrawDepositSecoundBankModel> bankModelList =new ArrayList<>();
        bankModelList.add(new ApplyWithdrawDepositSecoundBankModel());
        bankModelList.add(new ApplyWithdrawDepositSecoundBankModel());
        ApplyWithdrawDepositSecoundBankAdapter bankAdapter =new ApplyWithdrawDepositSecoundBankAdapter(this,bankModelList);
        listView_bank.setAdapter(bankAdapter);

        List<ApplyWithdrawDepositSecoundPayModel> payModelList =new ArrayList<>();
        payModelList.add(new ApplyWithdrawDepositSecoundPayModel());
        ApplyWithdrawDepositSecoundPayAdapter payAdapter =new ApplyWithdrawDepositSecoundPayAdapter(this,payModelList);
        listView_pay.setAdapter(payAdapter);

    }
    @OnClick({R.id.iv_add_bank})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.iv_add_bank:
                goActivity(AddToBankCardActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
