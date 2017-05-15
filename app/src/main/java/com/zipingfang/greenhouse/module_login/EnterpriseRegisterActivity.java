package com.zipingfang.greenhouse.module_login;

import android.Manifest;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.xilada.xldutils.activitys.SelectPhotoDialog;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.tool.IsMobilieNum;
import com.xilada.xldutils.utils.PermissionManager;
import com.xilada.xldutils.utils.Toast;
import com.zaaach.citypicker.CityPickerActivity;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * 企业注册
 * Created by Administrator on 2017/4/27.
 */

public class EnterpriseRegisterActivity extends TitleBarActivity {
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_protocol)
    TextView tv_protocol;
    @BindView(R.id.tv_position)
    TextView tv_position;
    @BindView(R.id.et_enterprise_name)
    EditText et_enterprise_name;
    @BindView(R.id.et_register_number)
    EditText et_register_number;
    @BindView(R.id.et_detail_position)
    EditText et_detail_position;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.iv_photo1)
    ImageView iv_photo1;
    @BindView(R.id.iv_photo2)
    ImageView iv_photo2;
    @BindView(R.id.iv_photo3)
    ImageView iv_photo3;
    @BindView(R.id.layout_position)
    LinearLayout layout_position;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private static final int PHOTO_RESULT_CODE_01=5;
    private static final int PHOTO_RESULT_CODE_02=6;
    private static final int PHOTO_RESULT_CODE_03=7;
    private static final int REQUEST_CODE_PICK_CITY=8;
    private String photo_img_01,photo_img_02,photo_img_03;
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_enterprise_register;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        tv_title.setText("注册");
        RxView.clicks(tv_next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (TextUtils.isEmpty(et_enterprise_name.getText().toString())) {
                            Toast.create(EnterpriseRegisterActivity.this).show("输入公司名称");
                            return;
                        }
                        if (TextUtils.isEmpty(et_register_number.getText().toString())) {
                            Toast.create(EnterpriseRegisterActivity.this).show("输入公司注册号");
                            return;
                        }
                        if (TextUtils.isEmpty(tv_position.getText().toString())) {
                            Toast.create(EnterpriseRegisterActivity.this).show("选择公司地区");
                            return;
                        }
                        if (TextUtils.isEmpty(et_detail_position.getText().toString())) {
                            Toast.create(EnterpriseRegisterActivity.this).show("输入公司详细地址");
                            return;
                        }
                        if (!TextUtils.isEmpty(et_phone.getText().toString())) {
                            if (!IsMobilieNum.isMobileNumber(et_phone.getText().toString())) {
                                Toast.create(EnterpriseRegisterActivity.this).show("推荐人手机号格式不正确");
                                return;
                            }
                            if (TextUtils.isEmpty(et_name.getText().toString())) {
                                Toast.create(EnterpriseRegisterActivity.this).show("输入联系人姓名");
                                return;
                            }
                            if (TextUtils.isEmpty(et_email.getText().toString())) {
                                Toast.create(EnterpriseRegisterActivity.this).show("输入联系人邮箱");
                                return;
                            }
                            if (TextUtils.isEmpty(photo_img_01)||TextUtils.isEmpty(photo_img_02)||TextUtils.isEmpty(photo_img_03)) {
                                Toast.create(EnterpriseRegisterActivity.this).show("选择营业执照");
                                return;
                            }
                            if (!checkbox.isChecked()) {
                                Toast.create(EnterpriseRegisterActivity.this).show("是否已阅读服务协议");
                                return;
                            }
                        }else{
                            if (TextUtils.isEmpty(et_name.getText().toString())) {
                                Toast.create(EnterpriseRegisterActivity.this).show("输入联系人姓名");
                                return;
                            }
                            if (TextUtils.isEmpty(et_email.getText().toString())) {
                                Toast.create(EnterpriseRegisterActivity.this).show("输入联系人邮箱");
                                return;
                            }
                            if (TextUtils.isEmpty(photo_img_01)||TextUtils.isEmpty(photo_img_02)||TextUtils.isEmpty(photo_img_03)) {
                                Toast.create(EnterpriseRegisterActivity.this).show("选择营业执照");
                                return;
                            }
                            if (!checkbox.isChecked()) {
                                Toast.create(EnterpriseRegisterActivity.this).show("是否已阅读服务协议");
                                return;
                            }


                        }

                    }
                });

    }
    @OnClick({R.id.tv_protocol,R.id.layout_position,R.id.iv_photo1,R.id.iv_photo2,R.id.iv_photo3,R.id.iv_back})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_protocol:

                break;
            case R.id.layout_position:
                goActivityForResult(CityPickerActivity.class,REQUEST_CODE_PICK_CITY);
                break;
            case R.id.iv_photo1:
                goActivityForResult(SelectPhotoDialog.class, PHOTO_RESULT_CODE_01);
                break;
            case R.id.iv_photo2:
                goActivityForResult(SelectPhotoDialog.class, PHOTO_RESULT_CODE_02);
                break;
            case R.id.iv_photo3:
                goActivityForResult(SelectPhotoDialog.class, PHOTO_RESULT_CODE_03);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_RESULT_CODE_01:
                    photo_img_01 = data.getStringExtra(SelectPhotoDialog.DATA);
                    if (!TextUtils.isEmpty(photo_img_01)) {
                        Glide.with(EnterpriseRegisterActivity.this).load(new File(photo_img_01)).asBitmap().into(iv_photo1);
                    }
                    break;
                case PHOTO_RESULT_CODE_02:
                    photo_img_02 = data.getStringExtra(SelectPhotoDialog.DATA);
                    if (!TextUtils.isEmpty(photo_img_02)) {
                        Glide.with(EnterpriseRegisterActivity.this).load(new File(photo_img_02)).asBitmap().into(iv_photo2);
                    }
                    break;
                case PHOTO_RESULT_CODE_03:
                    photo_img_03 = data.getStringExtra(SelectPhotoDialog.DATA);
                    if (!TextUtils.isEmpty(photo_img_03)) {
                        Glide.with(EnterpriseRegisterActivity.this).load(new File(photo_img_03)).asBitmap().into(iv_photo3);
                    }
                    break;
                case REQUEST_CODE_PICK_CITY:
                    String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                    if (!TextUtils.isEmpty(city)) {
                        tv_position.setText(city);
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
