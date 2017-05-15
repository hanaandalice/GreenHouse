package com.zipingfang.greenhouse.module_user.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.fragment.CollectMyMerchandiseFragment;
import com.zipingfang.greenhouse.module_user.fragment.CollectMyShopFragment;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/11.
 */

public class MyCollectActivity extends TitleBarActivity {

    @BindView(R.id.collect_segmentTabLayout)
    SegmentTabLayout collect_segmentTabLayout;
    @BindView(R.id.fragment_viewpager)
    ViewPager fragment_viewpager;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    private String[] mTitles = {"商  品", "店  铺"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String type;
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        mFragments.add(new CollectMyMerchandiseFragment());
        mFragments.add(new CollectMyShopFragment());
        fragment_viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        collect_segmentTabLayout.setTabData(mTitles);
        collect_segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
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
                collect_segmentTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (TextUtils.equals("1",type)) {
            fragment_viewpager.setCurrentItem(0);
        }else if (TextUtils.equals("2",type)){
            fragment_viewpager.setCurrentItem(1);
        }
    }
    @OnClick({R.id.iv_back})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
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
