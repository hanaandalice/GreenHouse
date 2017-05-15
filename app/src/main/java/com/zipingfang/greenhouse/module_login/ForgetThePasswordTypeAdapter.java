package com.zipingfang.greenhouse.module_login;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
public class ForgetThePasswordTypeAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList =new ArrayList<>();
    private final String[] titles = new String[3];
    public ForgetThePasswordTypeAdapter(Context context, FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList =fragmentList;
        titles[0] ="手机找回密码";
        titles[1] ="邮箱找回密码";
        titles[2] ="保密问题";
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
