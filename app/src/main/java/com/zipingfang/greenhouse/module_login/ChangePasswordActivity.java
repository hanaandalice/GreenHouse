package com.zipingfang.greenhouse.module_login;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * 重置密码
 * Created by Administrator on 2017/4/27.
 */

public class ChangePasswordActivity extends TitleBarActivity {
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @BindView(R.id.et_affirm_password)
    EditText et_affirm_password;
    @BindView(R.id.tv_next)
    TextView tv_next;
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("重置密码");
        unbinder = ButterKnife.bind(this);
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (et_new_password.getText().toString().length()<6) {
                            Toast.create(ChangePasswordActivity.this).show("输入6位以上的新密码");
                            return;
                        }
                        if (et_affirm_password.getText().toString().length()<6) {
                            Toast.create(ChangePasswordActivity.this).show("输入6位以上的确认密码");
                            return;
                        }
                        if (!TextUtils.equals(et_new_password.getText().toString(),et_affirm_password.getText().toString())) {
                            Toast.create(ChangePasswordActivity.this).show("密码不一致");
                            return;
                        }
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
