package com.zipingfang.greenhouse.module_user.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.xilada.xldutils.utils.DiaLogBuider;
import com.zipingfang.greenhouse.R;

/**
 * Created by Administrator on 2017/5/12.
 */

public class BalanceEditDialog {
    private DiaLogBuider diaLogBuider;
    public BalanceEditDialog(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_balance,null);
        diaLogBuider =new DiaLogBuider(context)
                .setContentView(view)
                .setFullScreen()
                .setGrvier(Gravity.CENTER)
                .setShow();

    }
    public DiaLogBuider getDiaLogBuider() {
        return diaLogBuider;
    }
}
