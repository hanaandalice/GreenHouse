package com.zipingfang.greenhouse;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.utils.PermissionManager;
import com.zipingfang.greenhouse.entity.TabEntity;
import com.zipingfang.greenhouse.module_classify.ClassifyFragment;
import com.zipingfang.greenhouse.module_discover.DiscoverFragment;
import com.zipingfang.greenhouse.module_home.HomeFragment;
import com.zipingfang.greenhouse.module_home.activity.CommodityRetailListActivity;
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleDetailsActivity;
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleListActivity;
import com.zipingfang.greenhouse.module_home.activity.MainAllActivity;
import com.zipingfang.greenhouse.module_login.LoginActivity;
import com.zipingfang.greenhouse.module_shopping.ShoppingCartFragment;
import com.zipingfang.greenhouse.module_shopping.activity.FavorableCommodityActivity;
import com.zipingfang.greenhouse.module_shopping.activity.GreenCardOrderBalanceActivity;
import com.zipingfang.greenhouse.module_shopping.activity.InventoryActivity;
import com.zipingfang.greenhouse.module_shopping.activity.OrderBalanceActivity;
import com.zipingfang.greenhouse.module_shopping.activity.OrderInvoiceTypeActivity;
import com.zipingfang.greenhouse.module_user.UserFragment;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends TitleBarActivity implements ViewTreeObserver.OnGlobalLayoutListener{
    @BindView(R.id.bottom_common_layout)
    CommonTabLayout bottom_common_layout;
    private int[] mIconUnselectIds = {
            R.mipmap.icon_rt_unselect_home, R.mipmap.icon_rt_unselect_classify,
            R.mipmap.icon_rt_unselect_discover, R.mipmap.icon_rt_unselect_shopping,R.mipmap.icon_rt_unselect_user};
    private int[] mIconSelectIds = {
            R.mipmap.icon_rt_select_home, R.mipmap.icon_rt_select_classify,
            R.mipmap.icon_rt_select_discover, R.mipmap.icon_rt_select_shopping,R.mipmap.icon_rt_select_user};
    private String[] mTitles =new String[5];
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private Unbinder unbinder;
    @BindView(R.id.mRegisterScroll)
    LinearLayout mRegisterScroll;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        PermissionManager.request(this, new String[]{
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, "正常使用必需的权限", 24);
        mTitles[0]="首页";
        mTitles[1]="分类";
        mTitles[2]="发现";
        mTitles[3]="购物车";
        mTitles[4]="我的";
        mFragments.add(new HomeFragment());
        mFragments.add(new ClassifyFragment());
        mFragments.add(new DiscoverFragment());
        mFragments.add(new ShoppingCartFragment());
        mFragments.add(new UserFragment());
        for (int i = 0; i < mFragments.size(); i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        bottom_common_layout.setTabData(mTabEntities, this, R.id.ct_fragment, mFragments);
        bottom_common_layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        mRegisterScroll.getViewTreeObserver().addOnGlobalLayoutListener(this);
//        goActivity(CommodityRetailListActivity.class);
//          goActivity(MainAllActivity.class);
//        goActivity(OrderInvoiceTypeActivity.class);
//        goActivity(GreenCardOrderBalanceActivity.class);
//        goActivity(InventoryActivity.class);
//        goActivity(OrderBalanceActivity.class);
    }

    @Override
    protected void onDestroy() {
        mRegisterScroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
    @Override
    public void onGlobalLayout() {
        //比较根布局与当前布局的大小
        int heightDiff = mRegisterScroll.getRootView().getHeight()- mRegisterScroll.getHeight();
        if(heightDiff >300){
            bottom_common_layout.setVisibility(View.GONE);
        }else{
            //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bottom_common_layout.setVisibility(View.VISIBLE);
                }
            },100);
        }
    }
}
