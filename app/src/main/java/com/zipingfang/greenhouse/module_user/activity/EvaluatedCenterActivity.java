package com.zipingfang.greenhouse.module_user.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.adapter.RecyclingPagerAdapter;
import com.xilada.xldutils.view.BannerLayout;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.fragment.MyEvaluationFragment;
import com.zipingfang.greenhouse.module_user.fragment.ProductEvaluationFragment;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/12.
 */

public class EvaluatedCenterActivity extends TitleBarActivity {
    @BindView(R.id.tv_product_evaluation)
    TextView tv_product_evaluation;
    @BindView(R.id.tv_additional_bask_in_single)
    TextView tv_additional_bask_in_single;
    @BindView(R.id.tv_my_evaluation)
    TextView tv_my_evaluation;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bannerLayout)
    BannerLayout bannerLayout;

    private Unbinder unbinder;
    private List<Fragment> fragmentList =new ArrayList<>();
    private RecyclingPagerAdapter bannerAdapter = null;
    private List<String> list = new ArrayList<>();
    @Override
    protected int setContentLayout() {
        return R.layout.activity_evaluated_center;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("评价中心");
        setRightButton("", getResources().getDrawable(R.mipmap.icon_more), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");
        unbinder = ButterKnife.bind(this);
        ProductEvaluationFragment productEvaluationFragment =new ProductEvaluationFragment();
        Bundle bundle =new Bundle();
        bundle.putString("type","1");
        productEvaluationFragment.setArguments(bundle);
        fragmentList.add(productEvaluationFragment);
        ProductEvaluationFragment productEvaluationFragment1 =new ProductEvaluationFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("type","2");
        productEvaluationFragment1.setArguments(bundle1);
        fragmentList.add(productEvaluationFragment1);
        MyEvaluationFragment myEvaluationFragment =new MyEvaluationFragment();
        fragmentList.add(myEvaluationFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        bannerLayout.setBannerItemListener(new BannerLayout.BannerItemListener() {
            @Override
            public void onItem(int position) {

            }

            @Override
            public void onPageSelected(int position) {

            }
        });

        bannerAdapter = new RecyclingPagerAdapter<String>(this, list, R.layout.item_banner_imgae) {
            @Override
            protected void onBind(int position, String data, ViewHolder holder) {
                Glide.with(EvaluatedCenterActivity.this).load(data).centerCrop().into(holder.<ImageView>bind(R.id.img));
            }
        };
        bannerLayout.setAdapter(bannerAdapter);
        bannerLayout.showIndicator(false);
        bannerLayout.startAutoScroll();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTable(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.tv_product_evaluation,R.id.tv_additional_bask_in_single,R.id.tv_my_evaluation})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.tv_product_evaluation:
                setTable(0);
                break;
            case R.id.tv_additional_bask_in_single:
                setTable(1);
                break;
            case R.id.tv_my_evaluation:
                setTable(2);
                break;
        }
    }
    private void setTable(int selected){
        if (selected==0) {
            tv_product_evaluation.setTextColor(getResources().getColor(R.color.bg_green));
            tv_additional_bask_in_single.setTextColor(getResources().getColor(R.color.text_color));
            tv_my_evaluation.setTextColor(getResources().getColor(R.color.text_color));
            viewPager.setCurrentItem(0);
        }else if (selected==1){
            tv_product_evaluation.setTextColor(getResources().getColor(R.color.text_color));
            tv_additional_bask_in_single.setTextColor(getResources().getColor(R.color.bg_green));
            tv_my_evaluation.setTextColor(getResources().getColor(R.color.text_color));
            viewPager.setCurrentItem(1);
        }else if (selected==2){
            tv_product_evaluation.setTextColor(getResources().getColor(R.color.text_color));
            tv_additional_bask_in_single.setTextColor(getResources().getColor(R.color.text_color));
            tv_my_evaluation.setTextColor(getResources().getColor(R.color.bg_green));
            viewPager.setCurrentItem(2);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
