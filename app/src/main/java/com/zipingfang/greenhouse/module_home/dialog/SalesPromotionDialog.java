package com.zipingfang.greenhouse.module_home.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xilada.xldutils.utils.DiaLogBuider;
import com.zipingfang.greenhouse.R;

/**
 * Created by Administrator on 2017/5/3.
 */

public class SalesPromotionDialog {
    private DiaLogBuider diaLogBuider;
    public  SalesPromotionDialog(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sales_promotion,null);
        diaLogBuider =new DiaLogBuider(context)
                .setContentView(view)
                .setFullScreen()
                .setGrvier(Gravity.CENTER)
                .setShow();
        ImageView iv_dismiss = (ImageView) view.findViewById(R.id.iv_dismiss);
        RelativeLayout layout_dismiss = (RelativeLayout) view.findViewById(R.id.layout_dismiss);
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogBuider.setDismiss();
            }
        });
        layout_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogBuider.setDismiss();
            }
        });
    }
    public DiaLogBuider getDiaLogBuider() {
        return diaLogBuider;
    }
}
