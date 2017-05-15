package com.zipingfang.greenhouse.module_login;

import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.tool.IsMobilieNum;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * 注册
 * Created by Administrator on 2017/4/27.
 */

public class RegisterActivity extends TitleBarActivity {
    @BindView(R.id.radio_layout)
    RadioGroup radio_layout;
    @BindView(R.id.radio_individual_registration)
    RadioButton radio_individual_registration;
    @BindView(R.id.radio_enterprise_register)
    RadioButton radio_enterprise_register;
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_send_code)
    TextView tv_send_code;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_enterprise_name)
    EditText et_enterprise_name;
    @BindView(R.id.et_setting_password)
    EditText et_setting_password;
    @BindView(R.id.et_affirm_password)
    EditText et_affirm_password;
    @BindView(R.id.layout_visibility_enterprise_name)
    LinearLayout layout_visibility_enterprise_name;
    @BindView(R.id.layout_protocol)
    LinearLayout layout_protocol;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private int num =0;
    private TimeCount timeCount;
    private String type="1";
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        tv_title.setText("注册");
        timeCount =new TimeCount(60000,1000);
        radio_individual_registration.setButtonDrawable(R.drawable.checked_green_r);
        radio_enterprise_register.setButtonDrawable(R.drawable.checked_green_r);
        radio_layout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_individual_registration:
                        layout_visibility_enterprise_name.setVisibility(View.GONE);
                        layout_protocol.setVisibility(View.VISIBLE);
                        tv_next.setText("确  定");
                        type ="1";
                        break;
                    case R.id.radio_enterprise_register:
                        layout_visibility_enterprise_name.setVisibility(View.VISIBLE);
                        layout_protocol.setVisibility(View.GONE);
                        tv_next.setText("下  一  步");
                        type ="2";
                        break;
                }
            }
        });
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (TextUtils.equals("2",type)){
                            if (TextUtils.isEmpty(et_enterprise_name.getText().toString())) {
                                Toast.create(RegisterActivity.this).show("输入企业简称");
                                return;
                            }
                        }
                        if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                            Toast.create(RegisterActivity.this).show("手机格式不正确");
                            return;
                        }
                        if (TextUtils.isEmpty(et_code.getText().toString())) {
                            Toast.create(RegisterActivity.this).show("输入验证码");
                            return;
                        }
                        if (et_setting_password.getText().toString().length()<6) {
                            Toast.create(RegisterActivity.this).show("输入6位以上设置密码");
                            return;
                        }
                        if (et_affirm_password.getText().toString().length()<6) {
                            Toast.create(RegisterActivity.this).show("输入6位以上确认密码");
                            return;
                        }
                        if (!TextUtils.equals(et_setting_password.getText().toString(),et_affirm_password.getText().toString())) {
                            Toast.create(RegisterActivity.this).show("密码不一致");
                            return;
                        }
                        if (TextUtils.equals("1",type)){
                            if (!checkbox.isChecked()) {
                                Toast.create(RegisterActivity.this).show("是否已阅读服务协议?");
                                return;
                            }
                        }
                        if (TextUtils.equals("1",type)){
                            Toast.create(RegisterActivity.this).show("注册成功");
                        }else if(TextUtils.equals("2",type)){
                            goActivity(EnterpriseRegisterActivity.class);
                        }

                    }
                });
    }
    @OnClick({R.id.tv_send_code,R.id.iv_back})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_send_code:
                if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                    Toast.create(RegisterActivity.this).show("手机格式不正确");
                    return;
                }
                tv_send_code.setClickable(false);
                num =60;
                timeCount.start();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
        if (null!=timeCount) {
            timeCount.cancel();
            timeCount =null;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        if (null!=timeCount) {
            timeCount.onFinish();
            timeCount.cancel();
        }
    }
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {// 计时完毕
            tv_send_code.setText("获取验证码");
            tv_send_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            num--;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_send_code.setText(num+"S");
                }
            });
            tv_send_code.setClickable(false);
        }
    }
}
