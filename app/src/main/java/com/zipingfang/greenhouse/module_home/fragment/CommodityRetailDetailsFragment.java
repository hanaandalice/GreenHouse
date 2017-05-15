package com.zipingfang.greenhouse.module_home.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xilada.xldutils.adapter.RecyclingPagerAdapter;
import com.xilada.xldutils.view.BannerLayout;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.entity.TabEntity;
import com.zipingfang.greenhouse.module_home.activity.CommodityRetailDetailsActivity;
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleDetailsActivity;
import com.zipingfang.greenhouse.module_home.dialog.CollarCouponsDialog;
import com.zipingfang.greenhouse.module_home.dialog.SalesPromotionDialog;
import com.zipingfang.greenhouse.view.customProgress.CustomProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CommodityRetailDetailsFragment  extends Fragment implements View.OnClickListener {
    private WebView webview;
    private List<String> list = new ArrayList<>();
    private BannerLayout bannerLayout;
    private TextView tv_cost_price,
            tv_select_position,//banner滑动到了第几
            tv_tv_quality_guarantee_period_ps,//保质期下面文字
            tv_label_wholesale,//批发标签
            tv_wholesale_contents,//标签内容
            tv_quality_guarantee_period;//保质期
    private LinearLayout layout_collar_coupons,
            layout_sales_promotion,
            layout_select_classify,
            layout_label_left,
            layout_label_right,
            test_layout;
    private CustomProgressBar cp_progressbar;

    private RecyclingPagerAdapter bannerAdapter = null;
    private FrameLayout layout_left;
    private CommonTabLayout commonTab;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};

    private String[] mTitles = {"商品介绍", "规格参数", "包装售后"};

    private int position =0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");
        return inflater.inflate(R.layout.fragment_goods_details_with_webview_retail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bannerLayout = (BannerLayout) view.findViewById(R.id.bannerLayout);
        tv_cost_price = (TextView) view.findViewById(R.id.tv_cost_price);
        layout_collar_coupons = (LinearLayout) view.findViewById(R.id.layout_collar_coupons);
        layout_sales_promotion = (LinearLayout) view.findViewById(R.id.layout_sales_promotion);
        layout_select_classify = (LinearLayout) view.findViewById(R.id.layout_select_classify);
        layout_left = (FrameLayout) view.findViewById(R.id.layout_left);
        tv_select_position = (TextView) view.findViewById(R.id.tv_select_position);
        tv_tv_quality_guarantee_period_ps = (TextView) view.findViewById(R.id.tv_tv_quality_guarantee_period_ps);
        tv_label_wholesale = (TextView) view.findViewById(R.id.tv_label_wholesale);
        tv_wholesale_contents = (TextView) view.findViewById(R.id.tv_wholesale_contents);
        tv_quality_guarantee_period = (TextView) view.findViewById(R.id.tv_quality_guarantee_period);
        layout_label_left = (LinearLayout) view.findViewById(R.id.layout_label_left);
        layout_label_right = (LinearLayout) view.findViewById(R.id.layout_label_right);
        test_layout = (LinearLayout) view.findViewById(R.id.test_layout);
        cp_progressbar = (CustomProgressBar) view.findViewById(R.id.cp_progressbar);

        webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl("https://github.com/ysnows");

        commonTab = (CommonTabLayout) view.findViewById(R.id.commonTab);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        commonTab.setTabData(mTabEntities);

        layout_collar_coupons.setOnClickListener(this);
        layout_sales_promotion.setOnClickListener(this);
        layout_select_classify.setOnClickListener(this);
        commonTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        cp_progressbar.setProgressDesc("剩余");
        cp_progressbar.setMaxProgress(100);
        cp_progressbar.setProgressColor(getResources().getColor(R.color.orange_f8));
        cp_progressbar.setBackground(getResources().getDrawable(R.drawable.radius_3dp_white_bg));
        cp_progressbar.setCurProgress(80,3000);
        test_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                switch (position) {
                    case 0:
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.bg_green));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.bg_green_bc));
                        tv_label_wholesale.setVisibility(View.GONE);
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.bg_green));
                        tv_wholesale_contents.setText("清仓特价");
                        cp_progressbar.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.bg_green));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.white));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.textColor_green_44));
                        break;
                    case 1:
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.blue_5e));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.blue_85));
                        cp_progressbar.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.transparency));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.textColor_blue_08));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.textColor_blue_08));
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.blue_5e));
                        tv_label_wholesale.setVisibility(View.GONE);
                        tv_wholesale_contents.setText("手机专享");
                        break;
                    case 2:
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.red_f2));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.red_f9));
                        cp_progressbar.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.red_d1));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.white));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.textColor_red_97));
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.red_f2));
                        tv_label_wholesale.setVisibility(View.GONE);
                        tv_wholesale_contents.setText("手机专享");
                        break;
                    case 3:
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.orange_f2));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.orange_f9));
                        cp_progressbar.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.orange_f2));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.white));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.white));
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.orange_f2));
                        tv_label_wholesale.setVisibility(View.GONE);
                        tv_wholesale_contents.setText("团购");
                        break;
                    case 4:
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.orange_f8));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.orange_fb));
                        cp_progressbar.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.orange_f8));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.white));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.textColor_orange_6c));
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.orange_f8));
                        tv_label_wholesale.setVisibility(View.GONE);
                        tv_wholesale_contents.setText("限时抢购");
                        break;
                    case 5://显示是否批发
                        position=-1;
                        layout_label_left.setBackgroundColor(getResources().getColor(R.color.bg_green));
                        layout_label_right.setBackgroundColor(getResources().getColor(R.color.bg_green_bc));
                        tv_label_wholesale.setTextColor(getResources().getColor(R.color.bg_green));
                        tv_label_wholesale.setVisibility(View.VISIBLE);
                        cp_progressbar.setVisibility(View.GONE);
                        tv_tv_quality_guarantee_period_ps.setVisibility(View.VISIBLE);
                        tv_tv_quality_guarantee_period_ps.setBackgroundColor(getResources().getColor(R.color.bg_green));
                        tv_tv_quality_guarantee_period_ps.setTextColor(getResources().getColor(R.color.white));
                        tv_quality_guarantee_period.setTextColor(getResources().getColor(R.color.textColor_green_44));
                        tv_wholesale_contents.setText("新品预售");
                        break;

                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_select_position.setText("1/" + list.size());
        bannerLayout.setBannerItemListener(new BannerLayout.BannerItemListener() {
            @Override
            public void onItem(int position) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_select_position.setText((position + 1) + "/" + list.size());
            }
        });
        bannerAdapter = new RecyclingPagerAdapter<String>(getActivity(), list, R.layout.item_banner_imgae) {
            @Override
            protected void onBind(int position, String data, ViewHolder holder) {
                Glide.with(getActivity()).load(data).into(holder.<ImageView>bind(R.id.img));
            }
        };

        bannerLayout.setAdapter(bannerAdapter);
        bannerLayout.startAutoScroll();
        tv_cost_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_collar_coupons:
                final CollarCouponsDialog collarCouponsDialog = new CollarCouponsDialog(getActivity());
                break;
            case R.id.layout_sales_promotion:
                final SalesPromotionDialog salesPromotionDialog = new SalesPromotionDialog(getActivity());
                break;
            case R.id.layout_select_classify:
                CommodityRetailDetailsActivity retailDetailsActivity = (CommodityRetailDetailsActivity) getActivity();
                retailDetailsActivity.getCommodityDrawer().openDrawer(GravityCompat.START);
                break;
        }
    }
}