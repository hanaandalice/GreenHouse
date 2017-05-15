package com.zipingfang.greenhouse.module_user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.dialog.WithdrawDepositDialog;
import com.zipingfang.greenhouse.module_user.fragment.BalanceAccountsFragment;
import com.zipingfang.greenhouse.module_user.fragment.CollectMyMerchandiseFragment;
import com.zipingfang.greenhouse.module_user.fragment.CollectMyShopFragment;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/12.
 */

public class AccountBalanceActivity extends TitleBarActivity {
    @BindView(R.id.balance_segmentTabLayout)
    SegmentTabLayout balance_segmentTabLayout;
    @BindView(R.id.fragment_viewpager)
    ViewPager fragment_viewpager;
    @BindView(R.id.tv_withdraw_deposit)
    TextView tv_withdraw_deposit;

    private Unbinder unbinder;
    private String[] mTitles = {"余额账目", "提现记录"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int setContentLayout() {
        return R.layout.activity_account_balance;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("账户余额");
        setRightButton("", getResources().getDrawable(R.mipmap.icon_more), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        unbinder = ButterKnife.bind(this);
        BalanceAccountsFragment balanceAccountsFragment =new BalanceAccountsFragment();
        Bundle bundle =new Bundle();
        bundle.putString("type","1");
        balanceAccountsFragment.setArguments(bundle);
        BalanceAccountsFragment balanceAccountsFragment1 =new BalanceAccountsFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("type","2");
        balanceAccountsFragment1.setArguments(bundle1);
        mFragments.add(balanceAccountsFragment);
        mFragments.add(balanceAccountsFragment1);
        fragment_viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        balance_segmentTabLayout.setTabData(mTitles);
        balance_segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                fragment_viewpager.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        fragment_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                balance_segmentTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @OnClick({R.id.tv_withdraw_deposit})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_withdraw_deposit:
                WithdrawDepositDialog withdrawDepositDialog =new WithdrawDepositDialog(AccountBalanceActivity.this);
                break;
        }
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
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
