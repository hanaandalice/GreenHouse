package com.zipingfang.greenhouse.module_shopping.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.xilada.xldutils.activitys.SelectPhotoDialog;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zaaach.citypicker.CityPickerActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_login.EnterpriseRegisterActivity;

import org.json.JSONException;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 订单支付
 * Created by Administrator on 2017/5/9.
 */

public class OrderPaySelectedActivity extends TitleBarActivity {
    @BindView(R.id.layout_line_pay)
    LinearLayout layout_line_pay;
    @BindView(R.id.layout_wechat_pay)
    LinearLayout layout_wechat_pay;
    @BindView(R.id.layout_alipay)
    LinearLayout layout_alipay;
    @BindView(R.id.layout_bank_card_pay)
    LinearLayout layout_bank_card_pay;
    @BindView(R.id.iv_wechat_pay)
    ImageView iv_wechat_pay;
    @BindView(R.id.iv_alipay)
    ImageView iv_alipay;
    @BindView(R.id.iv_layout_bank_card_pay)
    ImageView iv_layout_bank_card_pay;

    private Unbinder unbinder;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_order_pay_selected;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("订单支付");
        unbinder = ButterKnife.bind(this);


    }
    @OnClick({R.id.layout_line_pay,R.id.layout_wechat_pay,R.id.layout_alipay
            ,R.id.layout_bank_card_pay
            })
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.layout_wechat_pay:
                iv_wechat_pay.setVisibility(View.VISIBLE);
                iv_alipay.setVisibility(View.GONE);
                iv_layout_bank_card_pay.setVisibility(View.GONE);
                break;
            case R.id.layout_alipay:
                iv_wechat_pay.setVisibility(View.GONE);
                iv_alipay.setVisibility(View.VISIBLE);
                iv_layout_bank_card_pay.setVisibility(View.GONE);
                break;
            case R.id.layout_bank_card_pay:
                iv_wechat_pay.setVisibility(View.GONE);
                iv_alipay.setVisibility(View.GONE);
                iv_layout_bank_card_pay.setVisibility(View.VISIBLE);
                break;
            case R.id.layout_line_pay:
                goActivity(LinePayActivity.class);
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
