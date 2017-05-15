package com.zipingfang.greenhouse.module_user.dialog;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xilada.xldutils.utils.DiaLogBuider;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.activity.ApplyWithdrawDepositActivity;

/**
 * Created by Administrator on 2017/5/12.
 */

public class WithdrawDepositDialog {
    private DiaLogBuider diaLogBuider;
    public WithdrawDepositDialog(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_withdraw_deposit,null);
        diaLogBuider =new DiaLogBuider(context)
                .setContentView(view)
                .setFullScreen()
                .setGrvier(Gravity.CENTER)
                .setShow();
        TextView tv_go_to_withdraw_deposit = (TextView) view.findViewById(R.id.tv_go_to_withdraw_deposit);
        TextView tv_know = (TextView) view.findViewById(R.id.tv_know);
        tv_know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogBuider.setDismiss();
            }
        });
        tv_go_to_withdraw_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(context, ApplyWithdrawDepositActivity.class);
                context.startActivity(intent);
            }
        });
    }
    public DiaLogBuider getDiaLogBuider() {
        return diaLogBuider;
    }
}
