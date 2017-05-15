package com.zipingfang.greenhouse.module_user.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.BaseToBankAddressSelect;
import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import mlxy.utils.T;

/**
 * Created by Administrator on 2017/5/13.
 */

public class AddToBankCardActivity extends TitleBarActivity implements OnAddressSelectedListener{
    @BindView(R.id.layout_select_province)
    LinearLayout layout_select_province;
    @BindView(R.id.layout_select_city)
    LinearLayout layout_select_city;
    @BindView(R.id.layout_select_area)
    LinearLayout layout_select_area;
    @BindView(R.id.tv_province)
    TextView tv_province;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_area)
    TextView tv_area;

    private Unbinder unbinder;
    private BottomDialog dialog;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_add_to_bank_card;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("添加银行卡");
        setRightButton("", getResources().getDrawable(R.mipmap.icon_more), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        unbinder = ButterKnife.bind(this);
        BaseToBankAddressSelect selector = new BaseToBankAddressSelect(this);
        selector.setOnAddressSelectedListener(this);
        dialog = new BottomDialog(AddToBankCardActivity.this);
    }
    @OnClick({R.id.layout_select_province,R.id.layout_select_city,R.id.layout_select_area,})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.layout_select_province:
                showSelectAddressDialog();
                break;
            case R.id.layout_select_city:
                showSelectAddressDialog();
                break;
            case R.id.layout_select_area:
                showSelectAddressDialog();
                break;
        }
    }

    private void showSelectAddressDialog() {
        dialog.setOnAddressSelectedListener(AddToBankCardActivity.this);
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        if (province != null&&city!=null&&county!=null) {
            tv_province.setText(province.name);
            tv_city.setText(city.name);
            tv_area.setText(county.name);
        }

    }
}
