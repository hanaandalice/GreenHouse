package com.zipingfang.greenhouse.module_shopping.activity;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.tool.Densityuitl;
import com.xilada.xldutils.tool.Srceen;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.headerview.JDHeaderView;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrFrameLayout;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrHandler;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.activity.CommodityRetailDetailsActivity;
import com.zipingfang.greenhouse.module_home.adapter.CommodityRetailAdapter;
import com.zipingfang.greenhouse.module_home.fragment.CommodityScreenFragment;
import com.zipingfang.greenhouse.module_home.fragment.CommoditySelectedSpecificationsFragment;
import com.zipingfang.greenhouse.module_home.model.CheckedModel;
import com.zipingfang.greenhouse.module_home.model.CommodityRetailModel;
import com.zipingfang.greenhouse.module_shopping.adapter.FavorableCommodityAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *   换购
 * Created by Administrator on 2017/5/10.
 */
public class FavorableCommodityActivity extends TitleBarActivity implements PtrHandler, BaseQuickAdapter.RequestLoadMoreListener,AppBarLayout.OnOffsetChangedListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.header_list_view_frame)
    JDHeaderView header_list_view_frame;
    @BindView(R.id.layout_general)
    RelativeLayout layout_general;
    @BindView(R.id.layout_price)
    RelativeLayout layout_price;
    @BindView(R.id.layout_screen)
    RelativeLayout layout_screen;
    @BindView(R.id.cb_general)
    CheckBox cb_general;
    @BindView(R.id.cb_price)
    CheckBox cb_price;
    @BindView(R.id.tv_sales_volume)
    TextView tv_sales_volume;
    @BindView(R.id.tv_screen)
    TextView tv_screen;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.layout_list_general)
    FrameLayout layout_list_general;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.dl_commodity_drawer)
    DrawerLayout dl_commodity_drawer;
    @BindView(R.id.layout_left)
    FrameLayout layout_left;
    @BindView(R.id.layout_right)
    FrameLayout layout_right;
    @BindView(R.id.view_green_line)
    View view_green_line;
    @BindView(R.id.table_group)
    LinearLayout table_group;
    @BindView(R.id.mAppBar)
    AppBarLayout mAppBar;
    private RelativeLayout.LayoutParams greenLineLayoutParams;
    private int greenLineMargin;

    private FavorableCommodityAdapter favorableCommodityAdapter;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_favorable_commodity;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        hideTitle(true);
        unbinder = ButterKnife.bind(this);
        dl_commodity_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        greenLineMargin = Srceen.getScreen(this)[0]/4;
        greenLineLayoutParams  = (RelativeLayout.LayoutParams) view_green_line.getLayoutParams();
        greenLineLayoutParams.width = greenLineMargin- Densityuitl.dip2px(this,20);
        greenLineLayoutParams.height = Densityuitl.dip2px(this,1);
        view_green_line.setLayoutParams(greenLineLayoutParams);
        header_list_view_frame.setPtrHandler(this);
        LinearLayoutManager llManager =  new LinearLayoutManager(this);
        llManager.setSmoothScrollbarEnabled(true);
        llManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(llManager);
        favorableCommodityAdapter = new FavorableCommodityAdapter(this);
        favorableCommodityAdapter.setOnLoadMoreListener(this);
        favorableCommodityAdapter.setEnableLoadMore(true);
        recyclerView.setAdapter(favorableCommodityAdapter);
        List<CheckedModel> list =new ArrayList<>();
        list.add(new CheckedModel("综合排序",false));
        list.add(new CheckedModel("新品优先",false));
        list.add(new CheckedModel("评论数从高到低",false));
        listView.setAdapter(new ListAdapter(this,list));
        List<CommodityRetailModel> commodityRetailModelList =new ArrayList<>();
        commodityRetailModelList.add(new CommodityRetailModel());
        commodityRetailModelList.add(new CommodityRetailModel());
        commodityRetailModelList.add(new CommodityRetailModel());
        commodityRetailModelList.add(new CommodityRetailModel());
        commodityRetailModelList.add(new CommodityRetailModel());
        favorableCommodityAdapter.setNewData(commodityRetailModelList);
        favorableCommodityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goActivity(CommodityRetailDetailsActivity.class);
            }
        });
        favorableCommodityAdapter.setShoppingClickListener(new CommodityRetailAdapter.ShoppingClickListener() {
            @Override
            public void onClick() {
                dl_commodity_drawer.openDrawer(GravityCompat.START);
            }
        });

        CommoditySelectedSpecificationsFragment specificationsFragment = new CommoditySelectedSpecificationsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_left,specificationsFragment,"commoditySelectedSpecificationsFragment").commitAllowingStateLoss();
        specificationsFragment.setCloseDrawerLayoutListener(new CommodityScreenFragment.CloseDrawerLayoutListener() {
            @Override
            public void onCloseDrawerListener() {
                getDrawerClose();
            }
        });

        CommodityScreenFragment commodityScreenFragment = new CommodityScreenFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_right,commodityScreenFragment,"commodityScreenFragment").commitAllowingStateLoss();

        commodityScreenFragment.setCloseDrawerLayoutListener(new CommodityScreenFragment.CloseDrawerLayoutListener() {
            @Override
            public void onCloseDrawerListener() {
                getDrawerClose();
            }
        });

        table_group.removeAllViews();
        for (int i = 0; i < 5; i++) {
            ImageView imageView =new ImageView(this);
            if (i==0){
                imageView.setImageResource(R.mipmap.icon_mobile_phone_exclusive);
            }else if (i==1){
                imageView.setImageResource(R.mipmap.icon_table_discount_coupon);
            }else if (i==2){
                imageView.setImageResource(R.mipmap.icon_table_give_as_a_present);
            }else if (i==3){
                imageView.setImageResource(R.mipmap.icon_table_mitigate_a_punishment);
            }else if (i==4){
                imageView.setImageResource(R.mipmap.icon_table_group_buying);
            }
            table_group.addView(imageView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.rightMargin = dip2px(this,4);
            imageView.setLayoutParams(layoutParams);
        }
    }

    @OnCheckedChanged({R.id.cb_general,R.id.cb_price})
    void onChecked(CheckBox checkBox){
        switch (checkBox.getId()) {
            case R.id.cb_general:
                if (cb_general.isChecked()) {
                    layout_list_general.setVisibility(View.VISIBLE);
                }else{
                    layout_list_general.setVisibility(View.GONE);
                }
                break;
            case R.id.cb_price:
                if (cb_price.isChecked()) {
                    layout_list_general.setVisibility(View.GONE);
                }
                break;
        }
    }
    @OnClick({R.id.layout_general,R.id.layout_price,R.id.layout_screen,R.id.tv_sales_volume
            ,R.id.layout_list_general,R.id.iv_back})
    void onClicks(View view){
        switch (view.getId()) {
            case R.id.layout_general:
                cb_general.setChecked(true);
                cb_price.setChecked(false);
                tv_sales_volume.setTextColor(getColor(R.color.text_color));
                cb_price.setTextColor(getColor(R.color.text_color));
                cb_general.setTextColor(getColor(R.color.bg_green));
                setGreenLineMargin(1);
                break;
            case R.id.layout_price:
                cb_price.setChecked(true);
                cb_general.setChecked(false);
                tv_sales_volume.setTextColor(getColor(R.color.text_color));
                cb_general.setTextColor(getColor(R.color.text_color));
                cb_price.setTextColor(getColor(R.color.bg_green));
                setGreenLineMargin(3);
                break;
            case R.id.layout_screen:
                dl_commodity_drawer.openDrawer(GravityCompat.END);
                break;
            case R.id.tv_sales_volume:
                cb_general.setChecked(false);
                cb_price.setChecked(false);
                cb_general.setTextColor(getColor(R.color.text_color));
                cb_price.setTextColor(getColor(R.color.text_color));
                tv_sales_volume.setTextColor(getColor(R.color.bg_green));
                setGreenLineMargin(2);
                break;
            case R.id.layout_list_general:
                cb_general.setChecked(false);
                layout_list_general.setVisibility(View.GONE);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void setGreenLineMargin(int i) {
        greenLineLayoutParams.leftMargin =greenLineMargin*(i-1);
        view_green_line.setLayoutParams(greenLineLayoutParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    class ListAdapter extends com.xilada.xldutils.adapter.ListAdapter<CheckedModel>{
        public int checkedPosition=-1;
        /**
         * 列表适配器
         *
         * @param ctx      上下文
         * @param list     数据list
         */
        public ListAdapter(Context ctx, List<CheckedModel> list) {
            super(ctx, list, R.layout.item_list_checked);
        }
        @Override
        protected void onBind(final int position, final CheckedModel checkedModel, ViewHolder holder) {
            holder.setText(R.id.cb_checked,checkedModel.getContent());
            if (checkedPosition==position) {
                holder.setCheckbox(R.id.cb_checked,true);
                holder.<CheckBox>bind(R.id.cb_checked).setTextColor(getResources().getColor(R.color.bg_green));
            }else{
                holder.setCheckbox(R.id.cb_checked,false);
                holder.<CheckBox>bind(R.id.cb_checked).setTextColor(getResources().getColor(R.color.text_color));
            }
            holder.setOnClickListener(R.id.layout_checked, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkedPosition =position;
                    notifyDataSetChanged();
                    cb_general.setChecked(false);
                    cb_general.setText(checkedModel.getContent());

                }
            });
        }
    }
    public void getDrawerClose(){
        dl_commodity_drawer.closeDrawers();
    }
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAppBar.removeOnOffsetChangedListener(this);
    }

    private static final String TAG = "FavorableCommodityActiv";
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        header_list_view_frame.setEnabled(verticalOffset == 0);
    }
}
