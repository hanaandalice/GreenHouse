package com.zipingfang.greenhouse.module_login.fragment;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_login.ChangePasswordActivity;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/27.
 */

public class EncryptedOfPasswordFragment extends BaseLazyFragment{
    private TextView tv_next,tv_issue;
    private EditText et_answer;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_encrypted_of_password;
    }

    @Override
    protected void onFirstVisibleToUser() {
        tv_next =findViewById(R.id.tv_next);
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (TextUtils.isEmpty(et_answer.getText().toString())) {
                            Toast.create(getActivity()).show("未输入问题");
                            return;
                        }
                        goActivity(ChangePasswordActivity.class);
                    }
                });
    }

    @Override
    protected void onVisibleToUser() {
    }

    @Override
    protected void onInvisibleToUser() {
    }
}
