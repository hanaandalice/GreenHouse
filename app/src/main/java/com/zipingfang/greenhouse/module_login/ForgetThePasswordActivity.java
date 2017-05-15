package com.zipingfang.greenhouse.module_login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.utils.Toast;
import com.xilada.xldutils.utils.Utils;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.tool.CodeUtils;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * 忘记密码
 * Created by Administrator on 2017/4/27.
 */

public class ForgetThePasswordActivity extends TitleBarActivity {
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.et_account)
    EditText et_account;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.iv_code)
    ImageView iv_code;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_forget_the_password;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("忘记密码");
        unbinder = ButterKnife.bind(this);
        iv_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (TextUtils.isEmpty(et_account.getText().toString())) {
                            Toast.create(ForgetThePasswordActivity.this).show("请输入账户");
                            return;
                        }
                        if (!et_code.getText().toString().equalsIgnoreCase(CodeUtils.getInstance().getCode())) {
                            Toast.create(ForgetThePasswordActivity.this).show("验证码不正确");
                            return;
                        }
                        goActivity(ForgetThePasswordTypeActivity.class);
                    }
                });
        iv_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_code.setImageBitmap(CodeUtils.getInstance().createBitmap());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
