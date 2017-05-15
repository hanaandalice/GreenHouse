package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.RedemptionModel;

/**
 * Created by Administrator on 2017/5/10.
 */
public class RedemptionDialogAdapter extends BaseQuickAdapter<RedemptionModel,BaseViewHolder> {
    private int selectCheckedNum=0;
    public RedemptionDialogAdapter(Context context){
        super(R.layout.item_redemption_dialog);
    }
    @Override
    protected void convert(BaseViewHolder helper, RedemptionModel item, final int position) {
        helper.<TextView>getView(R.id.tv_price).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        helper.<CheckBox>getView(R.id.cb_checked).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selectCheckedNum++;
                }else{
                    selectCheckedNum--;
                }
                if (null!=checkedChangedListener) {
                    checkedChangedListener.onCheckedChanged(selectCheckedNum);
                }
            }
        });
    }
    public interface CheckedChangedListener{
        void onCheckedChanged(int position);
    }
    private CheckedChangedListener checkedChangedListener;
    public void setCheckedChangedListener(CheckedChangedListener checkedChangedListener){
        this.checkedChangedListener =checkedChangedListener;
    }
}
