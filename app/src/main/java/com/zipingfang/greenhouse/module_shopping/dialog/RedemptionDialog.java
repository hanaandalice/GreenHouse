package com.zipingfang.greenhouse.module_shopping.dialog;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.utils.DiaLogBuider;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.adapter.RedemptionDialogAdapter;
import com.zipingfang.greenhouse.module_shopping.model.RedemptionModel;

import java.util.ArrayList;
import java.util.List;

/**
 *  换购
 * Created by Administrator on 2017/5/10.
 */
public class RedemptionDialog {
    private DiaLogBuider diaLogBuider;
    public  RedemptionDialog(final Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_redemption,null);
        diaLogBuider =new DiaLogBuider(context)
                .setContentView(view)
                .setFullScreen()
                .setGrvier(Gravity.BOTTOM)
                .setShow();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        RedemptionDialogAdapter redemptionDialogAdapter =new RedemptionDialogAdapter(context);
        recyclerView.setAdapter(redemptionDialogAdapter);
        List<RedemptionModel> redemptionModelList =new ArrayList<>();
        redemptionModelList.add(new RedemptionModel());
        redemptionModelList.add(new RedemptionModel());
        redemptionModelList.add(new RedemptionModel());
        redemptionModelList.add(new RedemptionModel());
        redemptionDialogAdapter.addData(redemptionModelList);
        final TextView tv_selected_commodity_num = (TextView) view.findViewById(R.id.tv_selected_commodity_num);
        final TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_commodity_num = (TextView) view.findViewById(R.id.tv_commodity_num);
        final LinearLayout layout_favorable_money = (LinearLayout) view.findViewById(R.id.layout_favorable_money);
        final LinearLayout layout_favorable_num = (LinearLayout) view.findViewById(R.id.layout_favorable_num);
        tv_commodity_num.setText("/"+redemptionModelList.size()+"件");
        redemptionDialogAdapter.setCheckedChangedListener(new RedemptionDialogAdapter.CheckedChangedListener() {
            @Override
            public void onCheckedChanged(int position) {
                tv_selected_commodity_num.setText(position+"");
                if (position>0){
                    layout_favorable_money.setVisibility(View.GONE);
                    layout_favorable_num.setVisibility(View.VISIBLE);
                    tv_confirm.setBackgroundColor(context.getResources().getColor(R.color.bg_green));
                }else{
                    layout_favorable_money.setVisibility(View.VISIBLE);
                    layout_favorable_num.setVisibility(View.GONE);
                    tv_confirm.setBackgroundColor(context.getResources().getColor(R.color.black_95));

                }
            }
        });


    }
    public DiaLogBuider getDiaLogBuider() {
        return diaLogBuider;
    }
}
