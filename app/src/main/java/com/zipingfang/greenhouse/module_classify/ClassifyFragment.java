package com.zipingfang.greenhouse.module_classify;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xilada.xldutils.adapter.RecyclingPagerAdapter;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.view.BannerLayout;
import com.xilada.xldutils.view.utils.ViewHolder;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_classify.adapter.ClassifyIconAdapter;
import com.zipingfang.greenhouse.module_classify.adapter.ClassifyLogoAdapter;
import com.zipingfang.greenhouse.module_classify.adapter.TypeOfGoodsNameAdapter;
import com.zipingfang.greenhouse.module_classify.model.ClassifyIconModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyLogoModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyTypeModel;
import com.zipingfang.greenhouse.module_user.activity.ScanCodeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class ClassifyFragment extends BaseLazyFragment implements  BaseQuickAdapter.OnItemClickListener ,View.OnClickListener{
    private RecyclerView classic_recycle,
            classidty_icon_view;
    private TypeOfGoodsNameAdapter goodsNameAdapter;
    private List<ClassifyTypeModel> stringList;
    private BannerLayout bannerLayout;
    private RecyclingPagerAdapter bannerAdapter = null;
    private List<String> list = new ArrayList<>();

    private ClassifyIconAdapter classifyIconAdapter;
    private ImageView iv_scan;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_classify;
    }
    @Override
    protected void onFirstVisibleToUser() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b3.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b1.jpg");
        list.add("https://raw.githubusercontent.com/youth5201314/banner/master/app/src/main/res/mipmap-xhdpi/b2.jpg");

        classic_recycle =findViewById(R.id.classic_recycle);
        classidty_icon_view =findViewById(R.id.classidty_icon_view);
        iv_scan =findViewById(R.id.iv_scan);

        bannerLayout =findViewById(R.id.bannerLayout);
        bannerLayout.setBannerItemListener(new BannerLayout.BannerItemListener() {
            @Override
            public void onItem(int position) {

            }
            @Override
            public void onPageSelected(int position) {

            }
        });

        bannerAdapter = new RecyclingPagerAdapter<String>(getActivity(), list, R.layout.item_banner_imgae) {
            @Override
            protected void onBind(int position, String data, ViewHolder holder) {
                Glide.with(getActivity()).load(data).into(holder.<ImageView>bind(R.id.img));
            }
        };

        bannerLayout.setAdapter(bannerAdapter);
        bannerLayout.showIndicator(false);
        bannerLayout.startAutoScroll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        classic_recycle.setLayoutManager(layoutManager);
        goodsNameAdapter = new TypeOfGoodsNameAdapter(getActivity());
        goodsNameAdapter.setOnItemClickListener(this);
        classic_recycle.setAdapter(goodsNameAdapter);
        stringList =new ArrayList<>();
        stringList.add(new ClassifyTypeModel("默认",true));
        stringList.add(new ClassifyTypeModel("食品粮油",false));
        stringList.add(new ClassifyTypeModel("食品粮油",false));
        stringList.add(new ClassifyTypeModel("食品粮油",false));
        stringList.add(new ClassifyTypeModel("食品粮油",false));
        stringList.add(new ClassifyTypeModel("食品粮油",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("电脑配件",false));
        stringList.add(new ClassifyTypeModel("食品安全",false));
        stringList.add(new ClassifyTypeModel("食品安全",false));
        stringList.add(new ClassifyTypeModel("食品安全",false));
        stringList.add(new ClassifyTypeModel("食品安全",false));
        stringList.add(new ClassifyTypeModel("食品安全",false));
        stringList.add(new ClassifyTypeModel("日常用品",false));
        stringList.add(new ClassifyTypeModel("日常用品",false));
        stringList.add(new ClassifyTypeModel("日常用品",false));
        stringList.add(new ClassifyTypeModel("日常用品",false));
        stringList.add(new ClassifyTypeModel("日常用品",false));
        stringList.add(new ClassifyTypeModel("桌上办公",false));
        stringList.add(new ClassifyTypeModel("桌上办公",false));
        stringList.add(new ClassifyTypeModel("桌上办公",false));
        stringList.add(new ClassifyTypeModel("桌上办公",false));
        stringList.add(new ClassifyTypeModel("桌上办公",false));
        goodsNameAdapter.addData(stringList);


        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        layoutManager2.setSmoothScrollbarEnabled(true);
        layoutManager2.setAutoMeasureEnabled(true);
        classidty_icon_view.setLayoutManager(layoutManager2);
        classifyIconAdapter =new ClassifyIconAdapter(getActivity());
        classidty_icon_view.setAdapter(classifyIconAdapter);

        List<ClassifyIconModel> classifyIconList =new ArrayList<>();
        classifyIconList.add(new ClassifyIconModel());
        classifyIconList.add(new ClassifyIconModel());
        classifyIconList.add(new ClassifyIconModel());
        classifyIconList.add(new ClassifyIconModel());
        classifyIconList.add(new ClassifyIconModel());
        classifyIconAdapter.addData(classifyIconList);
        iv_scan.setOnClickListener(this);

    }
    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }

    /**
     *上一个被点击的item
     */
    private View lastClikeView = null;
    private int recycleViewCanShowHeight;
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(view.equals(lastClikeView))return;
        if (recycleViewCanShowHeight == 0){
            recycleViewCanShowHeight = classic_recycle.getHeight();
        }
        for (int i = 0; i < stringList.size(); i++) {
            if (position==i){
                stringList.get(i).setChecked(true);
            }else{
                stringList.get(i).setChecked(false);
            }
        }
        goodsNameAdapter.notifyDataSetChanged();
        if (recycleViewCanShowHeight > 0 && Build.VERSION.SDK_INT > 10){
            classic_recycle.smoothScrollBy(0, (int) (view.getY() - recycleViewCanShowHeight/2 + view.getPivotY()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_scan:
                goActivity(ScanCodeActivity.class);
                break;
        }
    }
}
