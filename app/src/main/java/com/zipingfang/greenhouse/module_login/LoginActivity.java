package com.zipingfang.greenhouse.module_login;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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
 * 登录
 * Created by Administrator on 2017/4/27.
 */

public class LoginActivity extends TitleBarActivity {
    @BindView(R.id.tv_cancle)
    TextView tv_cancle;
    @BindView(R.id.tv_register_immediately)
    TextView tv_register_immediately;
    @BindView(R.id.tv_forget_the_password)
    TextView tv_forget_the_password;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_alipay_login)
    TextView tv_alipay_login;
    @BindView(R.id.tv_wechat_login)
    TextView tv_wechat_login;
    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_password)
    EditText et_password;

    private boolean isChecked;
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);

        RxView.clicks(tv_login)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (TextUtils.isEmpty(et_account.getText().toString())) {
                            Toast.create(LoginActivity.this).show("请输入你的账户");
                            return;
                        }
                        if (et_password.getText().toString().length()<6) {
                            Toast.create(LoginActivity.this).show("请输入6位以上密码");
                            return;
                        }
                        login();
                    }
                });
    }
    /*
    * TODO 登录
    * **/
    private void login() {


    }

    @OnClick({R.id.tv_cancle
            ,R.id.iv_bottom_green
            ,R.id.iv_visibility_password
            ,R.id.tv_register_immediately
            ,R.id.tv_forget_the_password
            ,R.id.tv_alipay_login
            ,R.id.tv_wechat_login})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_cancle:
                finish();
                break;
            case R.id.iv_bottom_green:

                break;
            case R.id.iv_visibility_password:
                if (!isChecked) {
                    isChecked =true;
                    // 显示密码
                    et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    isChecked =false;
                    // 隐藏密码
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.tv_register_immediately:
                goActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_the_password:
                goActivity(ForgetThePasswordActivity.class);
                break;

            case R.id.tv_alipay_login:

                break;
            case R.id.tv_wechat_login:

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
