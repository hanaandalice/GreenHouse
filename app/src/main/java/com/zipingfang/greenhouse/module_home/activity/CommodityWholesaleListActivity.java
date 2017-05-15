package com.zipingfang.greenhouse.module_home.activity;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.zipingfang.greenhouse.module_home.adapter.CommodityWhlesaleAdapter;
import com.zipingfang.greenhouse.module_home.fragment.CommodityScreenFragment;
import com.zipingfang.greenhouse.module_home.fragment.CommoditySelectedSpecificationsFragment;
import com.zipingfang.greenhouse.module_home.model.CheckedModel;
import com.zipingfang.greenhouse.module_home.model.CommodityWholesaleModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 批量采购List 针对企业
 * Created by Administrator on 2017/5/2.
 */

public class CommodityWholesaleListActivity extends TitleBarActivity implements PtrHandler, BaseQuickAdapter.RequestLoadMoreListener {
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
    private RelativeLayout.LayoutParams greenLineLayoutParams;
    private int greenLineMargin;

    private CommodityWhlesaleAdapter commodityWhlesaleAdapter;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_commodity_wholesale;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commodityWhlesaleAdapter = new CommodityWhlesaleAdapter(R.layout.item_commodity_wholesale,this);
        commodityWhlesaleAdapter.setOnLoadMoreListener(this);
        commodityWhlesaleAdapter.setEnableLoadMore(false);
        recyclerView.setAdapter(commodityWhlesaleAdapter);
        List<CheckedModel> list =new ArrayList<>();
        list.add(new CheckedModel("综合排序",false));
        list.add(new CheckedModel("新品优先",false));
        list.add(new CheckedModel("评论数从高到低",false));
        listView.setAdapter(new ListAdapter(this,list));
        List<CommodityWholesaleModel> commodityWholesaleModelList =new ArrayList<>();
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWholesaleModelList.add(new CommodityWholesaleModel());
        commodityWhlesaleAdapter.setNewData(commodityWholesaleModelList);
        commodityWhlesaleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                goActivity(CommodityWholesaleDetailsActivity.class);
            }
        });
        commodityWhlesaleAdapter.setShoppingClickListener(new CommodityWhlesaleAdapter.ShoppingClickListener() {
            @Override
            public void onClick() {
                dl_commodity_drawer.openDrawer(GravityCompat.START);
            }
        });
        CommoditySelectedSpecificationsFragment specificationsFragment = new CommoditySelectedSpecificationsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_left,specificationsFragment,"commoditySelectedSpecificationsFragment").commitAllowingStateLoss();

        CommodityScreenFragment commodityScreenFragment = new CommodityScreenFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_right,commodityScreenFragment,"commodityScreenFragment").commitAllowingStateLoss();

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
}
