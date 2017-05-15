package com.zipingfang.greenhouse.module_login.fragment;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.tool.IsMobilieNum;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_login.ChangePasswordActivity;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/4/27.
 */

public class PhoneOfPasswordFragment extends BaseLazyFragment {
    private TextView tv_next,tv_send_code;
    private EditText et_phone,et_code;

    private int num =0;
    private TimeCount timeCount;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_photo_of_password;
    }
    @Override
    protected void onFirstVisibleToUser() {
        timeCount =new TimeCount(60000,1000);
        tv_next =findViewById(R.id.tv_next);
        tv_send_code =findViewById(R.id.tv_send_code);
        et_phone =findViewById(R.id.et_phone);
        et_code =findViewById(R.id.et_code);
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                            Toast.create(getActivity()).show("输入手机号码");
                            return;
                        }
                        if (TextUtils.isEmpty( et_code.getText().toString())) {
                            Toast.create(getActivity()).show("输入验证码");
                            return;
                        }
                        goActivity(ChangePasswordActivity.class);
                    }
                });
        tv_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                    Toast.create(getActivity()).show("输入手机号码");
                    return;
                }
                num=60;
                timeCount.start();
            }
        });

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {
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
            tv_send_code.setText("发送验证码");
            tv_send_code.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            num--;
            tv_send_code.setText(num+"S");
            tv_send_code.setClickable(false);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null!=timeCount) {
            timeCount.cancel();
            timeCount=null;
        }
    }
}
