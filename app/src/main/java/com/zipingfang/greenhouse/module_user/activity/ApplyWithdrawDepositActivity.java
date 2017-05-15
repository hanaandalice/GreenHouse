package com.zipingfang.greenhouse.module_user.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.tool.IsMobilieNum;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.tool.CodeUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ApplyWithdrawDepositActivity extends TitleBarActivity {
    @BindView(R.id.iv_code)
    ImageView iv_code;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.et_img_code)
    EditText et_img_code;
    private Unbinder unbinder;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_apply_withdraw_deposit;
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
        iv_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
    }

    @OnClick({R.id.iv_code,R.id.tv_next})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.iv_code:
                iv_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
                break;
            case R.id.tv_next:
                if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                    Toast.create(ApplyWithdrawDepositActivity.this).show("输入手机号码");
                }
                if (TextUtils.isEmpty(et_code.getText().toString())) {
                    Toast.create(ApplyWithdrawDepositActivity.this).show("输入验证码");
                }
                if (TextUtils.isEmpty(et_img_code.getText().toString())) {
                    Toast.create(ApplyWithdrawDepositActivity.this).show("输入图验证码");
                }
                goActivity(ApplyWithdrawDepositSecoundActivity.class);
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
