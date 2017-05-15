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

public class RushToPurchaseNoticeDialog {
    private DiaLogBuider diaLogBuider;
    public RushToPurchaseNoticeDialog(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_rush_to_purchase_notice,null);
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
