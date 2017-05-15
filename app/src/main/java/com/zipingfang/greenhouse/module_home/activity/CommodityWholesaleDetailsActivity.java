package com.zipingfang.greenhouse.module_home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.fragment.CommodityCommentFragment;
import com.zipingfang.greenhouse.module_home.fragment.CommoditySelectedSpecificationsFragment;
import com.zipingfang.greenhouse.module_home.fragment.ContentDetailFragment;
import com.zipingfang.greenhouse.module_home.fragment.CommodityWholesaleDetailFragment;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/2.
 */

public class CommodityWholesaleDetailsActivity extends TitleBarActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.dl_commodity_drawer)
    DrawerLayout dl_commodity_drawer;
    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_commodity_wholesale_details;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        dl_commodity_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        CommoditySelectedSpecificationsFragment specificationsFragment = new CommoditySelectedSpecificationsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_left,specificationsFragment,"commoditySelectedSpecificationsFragment")
                .commitAllowingStateLoss();
        MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);

    }

    @OnClick({R.id.iv_back})
    void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }

    /**
     * ViewPager的PagerAdapter
     */
    public class MinePagerAdapter extends FragmentPagerAdapter {
        Fragment[] fragments = new Fragment[]{new CommodityWholesaleDetailFragment(),new ContentDetailFragment(), new CommodityCommentFragment()};
        String[] titles = new String[]{"商品", "详情", "评价"};

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    public DrawerLayout getCommodityDrawer() {
        return dl_commodity_drawer;
    }
}
