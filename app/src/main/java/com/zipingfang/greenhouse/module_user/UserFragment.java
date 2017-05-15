package com.zipingfang.greenhouse.module_user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.activity.MyCollectActivity;
import com.zipingfang.greenhouse.module_user.activity.MyFootprintActivity;
import com.zipingfang.greenhouse.module_user.fragment.MyBullPostionFragment;
import com.zipingfang.greenhouse.module_user.fragment.MyShopFragment;
import com.zipingfang.greenhouse.view.CustomViewPager;
import com.zipingfang.greenhouse.view.customProgress.CustomProgressBar;
import com.zipingfang.greenhouse.view.gradation.GradationScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class UserFragment extends BaseLazyFragment implements View.OnClickListener{
    private LinearLayout layout_banner;
    private GradationScrollView gradationScrollview;
    private RelativeLayout titleLayout;
    private CustomViewPager viewPager;
    private SlidingTabLayout top_sliding;
    private TextView tv_my_order;
    private CustomProgressBar cp_progressbar;
    private TextView tv_merchandise_collection,
            tv_shop_collection,
            tv_my_tracks;
    private int titleHeight;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_user;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layout_banner =findViewById(R.id.layout_banner);
        titleLayout =findViewById(R.id.layout_title);
        viewPager =findViewById(R.id.viewPager);
        top_sliding =findViewById(R.id.top_sliding);
        tv_my_order =findViewById(R.id.tv_my_order);
        tv_my_tracks =findViewById(R.id.tv_my_tracks);
        cp_progressbar =findViewById(R.id.cp_progressbar);
        tv_merchandise_collection =findViewById(R.id.tv_merchandise_collection);
        tv_shop_collection =findViewById(R.id.tv_shop_collection);

        cp_progressbar.setProgressDesc("剩余");
        cp_progressbar.setMaxProgress(100);
        cp_progressbar.setProgressColor(Color.parseColor("#ff6460"));
        cp_progressbar.setBackgroundColor(Color.parseColor("#feaf02"));
        cp_progressbar.setCurProgress(80,3000);

        List<Fragment> fragmentList =new ArrayList<>();
        fragmentList.add(new MyBullPostionFragment());
        fragmentList.add(new MyShopFragment());
        viewPager.setOffscreenPageLimit(2);
        UserFragmentAdapter userFragmentAdapter =new UserFragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(userFragmentAdapter);
        top_sliding.setViewPager(viewPager);
        layout_banner.setFocusable(true);
        layout_banner.setFocusableInTouchMode(true);
        layout_banner.requestFocus();
        gradationScrollview =findViewById(R.id.gradationScrollview);
        ViewTreeObserver vto = layout_banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                titleLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int intw=View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int inth=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
                layout_banner.measure(intw, inth);
                titleHeight = layout_banner.getMeasuredHeight();
                gradationScrollview.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                        // TODO Auto-generated method stub
                        if (y <= 0) {   //设置标题的背景颜色
                            titleLayout.setBackgroundColor(Color.argb((int) 0, 128,198,67));
                        } else if (y > 0 && y <= titleHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                            float scale = (float) y / titleHeight;
                            float alpha = (255 * scale);
                            titleLayout.setBackgroundColor(Color.argb((int) alpha, 128,198,67));
                        } else {    //滑动到banner下面设置普通颜色
                            titleLayout.setBackgroundColor(Color.argb((int) 255, 128,198,67));
                        }
                    }
                });
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    tv_my_order.setVisibility(View.GONE);
                }else{
                    tv_my_order.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tv_merchandise_collection.setOnClickListener(this);
        tv_shop_collection.setOnClickListener(this);
        tv_my_tracks.setOnClickListener(this);
    }

    @Override
    protected void onFirstVisibleToUser() {

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
    @Override
    public void onClick(View v) {
        Bundle bundle =new Bundle();
        switch (v.getId()) {
            case R.id.tv_merchandise_collection:
                bundle.putString("type","1");
                goActivity(MyCollectActivity.class,bundle);;
                break;
            case R.id.tv_shop_collection:
                bundle.putString("type","2");
                goActivity(MyCollectActivity.class,bundle);
                break;
            case R.id.tv_my_tracks:
                goActivity(MyFootprintActivity.class);
                break;
        }
    }
}
