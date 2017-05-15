package com.zipingfang.greenhouse.module_home;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.tool.Densityuitl;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.JD_module.headerview.JDHeaderView;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrFrameLayout;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrHandler;
import com.zipingfang.greenhouse.R;

/**
 * Created by Administrator on 2017/4/28.
 */

public class HomeFragment extends BaseLazyFragment  implements JDHeaderView.RefreshDistanceListener{
    private RecyclerView recyclerView;
    private JDHeaderView header_list_view_frame;
    private LinearLayout homeTitleBarLayout;

    private int distanceY;
    private int titleHeight;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeTitleBarLayout =findViewById(R.id.homeTitleBarLayout);
        int intw=View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int inth=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        homeTitleBarLayout.measure(intw, inth);
        titleHeight = homeTitleBarLayout.getMeasuredHeight();
        recyclerView =findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(Densityuitl.dip2px(getActivity(),3)));
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                distanceY += dy;
//                if (distanceY <= 0) {   //设置标题的背景颜色
//                    homeTitleBarLayout.setBackgroundColor(Color.argb((int) 0, 255,255,255));
//                } else if (distanceY > 0 && distanceY <= titleHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//                    float scale = (float) distanceY / titleHeight;
//                    float alpha = (255 * scale);
//                    homeTitleBarLayout.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
//                } else {    //滑动到banner下面设置普通颜色
//                    homeTitleBarLayout.setBackgroundColor(Color.argb((int) 255, 255,255,255));
//                }
//            }
//        });
        initRefresh();
    }

    private void initRefresh() {
        header_list_view_frame =findViewById(R.id.header_list_view_frame);
        header_list_view_frame.setOnRefreshDistanceListener(this);
        header_list_view_frame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Toast.create(getActivity()).show("upDATA");
                header_list_view_frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },1000);
            }
        });
    }

    @Override
    protected void onFirstVisibleToUser() {
        Toast.create(getActivity()).show("first");
    }

    @Override
    protected void onVisibleToUser() {
    }

    @Override
    protected void onInvisibleToUser() {

    }

    @Override
    public void onPositionChange(int currentPosY) {
        if (currentPosY > 0) {
            if (homeTitleBarLayout.getVisibility() == View.VISIBLE) {
                homeTitleBarLayout.setVisibility(View.GONE);
            }
        } else {
            if (homeTitleBarLayout.getVisibility() == View.GONE) {
                homeTitleBarLayout.setVisibility(View.VISIBLE);
                distanceY = 0;
            }
        }
    }
}
