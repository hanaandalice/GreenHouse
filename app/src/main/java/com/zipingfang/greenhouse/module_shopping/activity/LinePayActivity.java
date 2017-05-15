package com.zipingfang.greenhouse.module_shopping.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xilada.xldutils.activitys.SelectPhotoDialog;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 线下支付
 * Created by Administrator on 2017/5/10.
 */

public class LinePayActivity extends TitleBarActivity {

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    private Unbinder unbinder;

    private static final int PHOTO_RESULT_CODE=5;
    private String photoUrl;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_line_pay;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("线下银行汇款");
        unbinder = ButterKnife.bind(this);
    }
    @OnClick({R.id.iv_photo})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.iv_photo:
                goActivityForResult(SelectPhotoDialog.class,PHOTO_RESULT_CODE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_RESULT_CODE:
                    photoUrl = data.getStringExtra(SelectPhotoDialog.DATA);
                    if (!TextUtils.isEmpty(photoUrl)) {
                        Glide.with(LinePayActivity.this).load(new File(photoUrl)).asBitmap().into(iv_photo);
                    }
                    break;
            }
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
