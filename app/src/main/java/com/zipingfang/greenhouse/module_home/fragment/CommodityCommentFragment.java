package com.zipingfang.greenhouse.module_home.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xilada.xldutils.view.flawtaglayout.FlowTagLayout;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.headerview.JDHeaderView;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrFrameLayout;
import com.zipingfang.greenhouse.JD_module.widget.pulltorefresh.PtrHandler;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.adapter.CommodityCommentAdapter;
import com.zipingfang.greenhouse.module_home.adapter.CommodityCommentFlowAdapter;
import com.zipingfang.greenhouse.module_home.model.CommodityCommentModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;


/**
 * 商品总体评论
 * A simple {@link Fragment} subclass.
 */
public class CommodityCommentFragment extends Fragment implements View.OnClickListener,PtrHandler, BaseQuickAdapter.RequestLoadMoreListener {

    private TextView tv_all,
            tv_high_opinion,
            tv_moderate_opinion,
            tv_negative_opinion,
            tv_the_sun;
    private FlowTagLayout fl_my_container;
    private RecyclerView recyclerView;
    private JDHeaderView header_list_view_frame;
    private CommodityCommentAdapter commodityCommentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commodity_comment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_all = (TextView) view.findViewById(R.id.tv_all);
        tv_high_opinion = (TextView) view.findViewById(R.id.tv_high_opinion);
        tv_moderate_opinion = (TextView) view.findViewById(R.id.tv_moderate_opinion);
        tv_negative_opinion = (TextView) view.findViewById(R.id.tv_negative_opinion);
        tv_the_sun = (TextView) view.findViewById(R.id.tv_the_sun);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        header_list_view_frame = (JDHeaderView) view.findViewById(R.id.header_list_view_frame);

        fl_my_container = (FlowTagLayout) view.findViewById(R.id.fl_my_container);
        fl_my_container.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        List<String> stringList =new ArrayList<>();
        stringList.add("味道好（324）");
        stringList.add("包装好（24）");
        stringList.add("赞（4）");
        CommodityCommentFlowAdapter commentFlowAdapter =  new CommodityCommentFlowAdapter(getActivity());
        fl_my_container.setAdapter(commentFlowAdapter);
        commentFlowAdapter.onlyAddAll(stringList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new MyRecyclerDetorration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        commodityCommentAdapter = new CommodityCommentAdapter(getActivity());
        recyclerView.setAdapter(commodityCommentAdapter);

        commodityCommentAdapter.setEnableLoadMore(false);
        List<CommodityCommentModel> commentModelList =new ArrayList<>();
        commentModelList.add(new CommodityCommentModel());
        commentModelList.add(new CommodityCommentModel());
        commentModelList.add(new CommodityCommentModel());
        commentModelList.add(new CommodityCommentModel());
        commentModelList.add(new CommodityCommentModel());
        commodityCommentAdapter.setNewData(commentModelList);
        commodityCommentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        tv_all.setOnClickListener(this);
        tv_high_opinion.setOnClickListener(this);
        tv_moderate_opinion.setOnClickListener(this);
        tv_negative_opinion.setOnClickListener(this);
        tv_the_sun.setOnClickListener(this);
        header_list_view_frame.setPtrHandler(this);
        commodityCommentAdapter.setOnLoadMoreListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                tv_all.setTextColor(getResources().getColor(R.color.bg_green));
                tv_high_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_moderate_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_negative_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_the_sun.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_high_opinion:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_high_opinion.setTextColor(getResources().getColor(R.color.bg_green));
                tv_moderate_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_negative_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_the_sun.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_moderate_opinion:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_high_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_moderate_opinion.setTextColor(getResources().getColor(R.color.bg_green));
                tv_negative_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_the_sun.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_negative_opinion:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_high_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_moderate_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_negative_opinion.setTextColor(getResources().getColor(R.color.bg_green));
                tv_the_sun.setTextColor(getResources().getColor(R.color.text_color));
                break;
            case R.id.tv_the_sun:
                tv_all.setTextColor(getResources().getColor(R.color.text_color));
                tv_high_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_moderate_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_negative_opinion.setTextColor(getResources().getColor(R.color.text_color));
                tv_the_sun.setTextColor(getResources().getColor(R.color.bg_green));
                break;
        }
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
