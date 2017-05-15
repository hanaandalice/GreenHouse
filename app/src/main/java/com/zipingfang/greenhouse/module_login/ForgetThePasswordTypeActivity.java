package com.zipingfang.greenhouse.module_login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_login.fragment.EmailOfPasswordFragment;
import com.zipingfang.greenhouse.module_login.fragment.EncryptedOfPasswordFragment;
import com.zipingfang.greenhouse.module_login.fragment.PhoneOfPasswordFragment;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 忘记密码第二步
 * Created by Administrator on 2017/4/27.
 */

public class ForgetThePasswordTypeActivity extends TitleBarActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.slding_tab)
    SlidingTabLayout slding_tab;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_forget_the_password_type;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("忘记密码");
        unbinder = ButterKnife.bind(this);
        viewPager.setOffscreenPageLimit(2);
        List<Fragment> fragmentList =new ArrayList<>();
        PhoneOfPasswordFragment fragment1 =new PhoneOfPasswordFragment();
        Bundle bundle1 =new Bundle();
        bundle1.putString("type","1");
        fragment1.setArguments(bundle1);
        EmailOfPasswordFragment fragment2 =new EmailOfPasswordFragment();
        Bundle bundle2 =new Bundle();
        bundle2.putString("type","2");
        fragment2.setArguments(bundle2);
        EncryptedOfPasswordFragment fragment3 =new EncryptedOfPasswordFragment();
        Bundle bundle3 =new Bundle();
        bundle3.putString("type","3");
        fragment3.setArguments(bundle3);
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        ForgetThePasswordTypeAdapter participateInActivityAdapter =new ForgetThePasswordTypeAdapter(this,getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(participateInActivityAdapter);
        slding_tab.setViewPager(viewPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
