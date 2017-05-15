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
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleDetailsActivity;
import com.zipingfang.greenhouse.module_home.dialog.CollarCouponsDialog;
import com.zipingfang.greenhouse.module_home.dialog.SalesPromotionDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommodityWholesaleDetailFragment extends Fragment implements View.OnClickListener{
    private WebView webview;
    private List<String> list = new ArrayList<>();
    private BannerLayout bannerLayout;
    private TextView tv_cost_price,
            tv_select_position;//banner滑动到了第几
    private LinearLayout layout_collar_coupons,
            layout_sales_promotion,
            layout_select_classify;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");
        return inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
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

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_select_position.setText("1/"+list.size());
        bannerLayout.startAutoScroll();
        bannerLayout.setBannerItemListener(new BannerLayout.BannerItemListener() {
            @Override
            public void onItem(int position) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_select_position.setText((position+1)+"/"+list.size());
            }
        });
        bannerAdapter =new RecyclingPagerAdapter<String>(getActivity(),list,R.layout.item_banner_imgae) {
            @Override
            protected void onBind(int position, String data, ViewHolder holder) {
                Glide.with(getActivity()).load(data).into(holder.<ImageView>bind(R.id.img));
            }
        };

        bannerLayout.setAdapter(bannerAdapter);
        tv_cost_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_collar_coupons:
                final  CollarCouponsDialog collarCouponsDialog =new CollarCouponsDialog(getActivity());
                break;
            case R.id.layout_sales_promotion:
                final SalesPromotionDialog salesPromotionDialog =new SalesPromotionDialog(getActivity());
                break;
            case R.id.layout_select_classify:
                CommodityWholesaleDetailsActivity detailsActivity = (CommodityWholesaleDetailsActivity) getActivity();
                detailsActivity.getCommodityDrawer().openDrawer(GravityCompat.START);
                break;
        }
    }

}
