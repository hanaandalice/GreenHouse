package com.zipingfang.greenhouse.module_user.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.dialog.WithdrawDepositDialog;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
/**
 *
 * Created by Administrator on 2017/5/11.
 */

public class MyWalletActivity extends TitleBarActivity {
    @BindView(R.id.layout_my_balance)
    LinearLayout layout_my_balance;
    @BindView(R.id.tv_withdraw_deposit)
    TextView tv_withdraw_deposit;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("我的钱包");
        setRightButton("", getResources().getDrawable(R.mipmap.icon_more), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        unbinder = ButterKnife.bind(this);

    }
    @OnClick({R.id.layout_my_balance,R.id.tv_withdraw_deposit})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.layout_my_balance:
                goActivity(AccountBalanceActivity.class);
                break;
            case R.id.tv_withdraw_deposit:
                WithdrawDepositDialog withdrawDepositDialog =new WithdrawDepositDialog(MyWalletActivity.this);
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
