package com.zipingfang.greenhouse.module_discover;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.tool.Densityuitl;
import com.xilada.xldutils.tool.Srceen;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.adapter.DiscoverAdapter;
import com.zipingfang.greenhouse.module_discover.adapter.DiscoverLableAdapter;
import com.zipingfang.greenhouse.module_discover.model.DiscoverLableModel;
import com.zipingfang.greenhouse.module_discover.model.DiscoverModel;
import com.zipingfang.greenhouse.module_user.activity.ScanCodeActivity;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;
import com.zipingfang.greenhouse.view.zoompager.ZoomHeaderView;
import com.zipingfang.greenhouse.view.zoompager.ZoomHeaderViewPager;

import java.util.ArrayList;
import java.util.List;
/**
 * 发现
 * Created by Administrator on 2017/4/28.
 */
public class DiscoverFragment extends BaseLazyFragment implements View.OnClickListener{
    private RecyclerView h_recycler,
            cover_recycler;
    private static final String TAG = "DiscoverFragment";
    private DiscoverLableAdapter discoverLableAdapter;
    private DiscoverAdapter discoverAdapter;
    private ZoomHeaderView zoomHeader;
    private ZoomHeaderViewPager zoom_viewpager;
    private LinearLayout layout_zoom,
            indicator;
    private ImageView iv_scan;

    private String indicatorBgColor = "#00000000";
    private int indicatorHeight = 20;//默认高度，单位dip
    private ImageView[] indicatorViews;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_discover;
    }
    @Override
    protected void onFirstVisibleToUser() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zoomHeader =findViewById(R.id.zoomHeader);
        layout_zoom =findViewById(R.id.layout_zoom);
        zoom_viewpager =findViewById(R.id.zoom_viewpager);
        iv_scan =findViewById(R.id.iv_scan);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) zoom_viewpager.getLayoutParams();
        layoutParams.width =(Srceen.getScreen(getActivity())[0]- Densityuitl.dip2px(getActivity(),32))/5*3;
        layoutParams.height =LinearLayout.LayoutParams.WRAP_CONTENT;
        zoom_viewpager.setLayoutParams(layoutParams);

        h_recycler =findViewById(R.id.h_recycler);
        h_recycler.setLayoutManager(new GridLayoutManager(getActivity(),4));
        discoverLableAdapter = new DiscoverLableAdapter(getActivity());
        h_recycler.setAdapter(discoverLableAdapter);
        List<DiscoverLableModel> lableModelList =new ArrayList<>();
        lableModelList.add(new DiscoverLableModel());
        lableModelList.add(new DiscoverLableModel());
        lableModelList.add(new DiscoverLableModel());
        lableModelList.add(new DiscoverLableModel());
        discoverLableAdapter.addData(lableModelList);

        cover_recycler =findViewById(R.id.cover_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        cover_recycler.setLayoutManager(layoutManager);
        cover_recycler.addItemDecoration(new MyRecyclerDetorration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_line));

        discoverAdapter = new DiscoverAdapter(getActivity());
        cover_recycler.setAdapter(discoverAdapter);
        List<DiscoverModel> modelList =new ArrayList<>();
        modelList.add(new DiscoverModel());
        modelList.add(new DiscoverModel());
        modelList.add(new DiscoverModel());
        modelList.add(new DiscoverModel());
        modelList.add(new DiscoverModel());
        modelList.add(new DiscoverModel());
        discoverAdapter.addData(modelList);

        zoom_viewpager.setAdapter(new Adapter());
        zoom_viewpager.setOffscreenPageLimit(4);
        zoom_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (indicator!=null){
                    setImageBackground(position);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (layout_zoom !=null){
            layout_zoom.removeView(indicator);
            indicator = null;
        }
        createIndicatorLayout();
        iv_scan.setOnClickListener(this);
    }
    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }

    private int[] imgs = { R.mipmap.test_goods_discover, R.mipmap.test_goods_discover, R.mipmap.test_goods_discover };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_scan:
                goActivity(ScanCodeActivity.class);
                break;
        }
    }

    class Adapter extends PagerAdapter {
        public Adapter() {
            views = new ArrayList<>();
            views.add(View.inflate(getActivity(), R.layout.item_discover_zoom_head, null));
//            views.get(0).findViewById(R.id.btn_buy).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
            views.add(View.inflate(getActivity(), R.layout.item_discover_zoom_head, null));
            views.add(View.inflate(getActivity(), R.layout.item_discover_zoom_head, null));
        }

        private ArrayList<View> views;

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            views.get(position).findViewById(R.id.iv_vocer_bg).setBackground(getResources().getDrawable(imgs[position]));
            TextView tv_price = (TextView) views.get(position).findViewById(R.id.tv_price);
            tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            container.addView(views.get(position));

            return views.get(position);
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
    }

    private void createIndicatorLayout(){
        indicator = new LinearLayout(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,dip2px(indicatorHeight));
        params.gravity = Gravity.BOTTOM;
        indicator.setLayoutParams(params);
        indicator.setGravity(Gravity.CENTER);
        indicator.setOrientation(LinearLayout.HORIZONTAL);
        indicator.setBackgroundColor(Color.parseColor(indicatorBgColor));
        addIndicator();
        //添加小圆点
        layout_zoom.addView(indicator);
    }
    private void addIndicator(){
        indicatorViews = new ImageView[imgs.length];
        for (int i = 0;i<imgs.length;i++){
            ImageView view = new ImageView(getContext());
            indicatorViews[i] = view;
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(dip2px(5),dip2px(5));
            params.rightMargin = dip2px(5);
            view.setLayoutParams(params);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setBackgroundResource(com.xilada.xldutils.R.drawable.page_indicator);
            if (i == 0){
                view.setSelected(true);
            }
            indicator.addView(view);
        }
    }
    /**
     * 设置选中的tip的背景
     * @param selectItems 选中项
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<indicatorViews.length; i++){
            if(i == selectItems){
                indicatorViews[i].setSelected(true);
            }else{
                indicatorViews[i].setSelected(false);
            }
        }
    }
    private int dip2px(int dip){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5f);
    }
}
