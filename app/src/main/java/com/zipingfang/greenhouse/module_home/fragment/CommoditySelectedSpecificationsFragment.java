package com.zipingfang.greenhouse.module_home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.xilada.xldutils.tool.Densityuitl;
import com.xilada.xldutils.utils.Toast;
import com.xilada.xldutils.view.flawtaglayout.FlowTagLayout;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleListActivity;
import com.zipingfang.greenhouse.module_home.adapter.CommodityFlowTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class CommoditySelectedSpecificationsFragment extends BaseLazyFragment implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener{
    private LinearLayout layout_flow_tag;
    private ImageView iv_commodity_close;
    private TextView add,
            sub,
            tv_add_shopping_cat;
    private FrameLayout mRegisterScroll;
    private EditText goodsNum;
    private int num=1;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commodity_selected_specifications;
    }

    @Override
    protected void onFirstVisibleToUser() {
        Toast.create(getActivity()).show("onFirstVisibleToUser");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        iv_commodity_close =findViewById(R.id.iv_commodity_close);
        mRegisterScroll =findViewById(R.id.mRegisterScroll);
        layout_flow_tag =findViewById(R.id.layout_flow_tag);
        goodsNum =findViewById(R.id.goodsNum);
        add =findViewById(R.id.add);
        sub =findViewById(R.id.sub);
        tv_add_shopping_cat =findViewById(R.id.tv_add_shopping_cat);
        
        View view =new View(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,Densityuitl.dip2px(getActivity(),0.5f));
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(getResources().getColor(R.color.grey_line));
        layout_flow_tag.addView(view);

        TextView textView =new TextView(getActivity());
        LinearLayout.LayoutParams layoutParams2 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.leftMargin =Densityuitl.dip2px(getActivity(),10);
        layoutParams2.topMargin =Densityuitl.dip2px(getActivity(),10);
        textView.setLayoutParams(layoutParams2);
        textView.setTextColor(getResources().getColor(R.color.grey_b5));
        textView.setTextSize(12);
        textView.setText("规格:");
        layout_flow_tag.addView(textView);

        FlowTagLayout flowTagLayout =new FlowTagLayout(getActivity());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams3.leftMargin =Densityuitl.dip2px(getActivity(),10);
        flowTagLayout.setLayoutParams(layoutParams3);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        List<String> list1 =new ArrayList<>();
        list1.add("550ML/1箱");
        list1.add("250ML/5箱");
        list1.add("450ML/6箱");
        CommodityFlowTabAdapter tagAdapter1 =new CommodityFlowTabAdapter(getActivity());
        flowTagLayout.setAdapter(tagAdapter1);
        tagAdapter1.onlyAddAll(list1);
        layout_flow_tag.addView(flowTagLayout);

        View view2 =new View(getActivity());
        LinearLayout.LayoutParams layoutParams4 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                Densityuitl.dip2px(getActivity(),0.5f));
        layoutParams4.topMargin = Densityuitl.dip2px(getActivity(),10f);
        view2.setLayoutParams(layoutParams4);
        view2.setBackgroundColor(getResources().getColor(R.color.grey_line));
        layout_flow_tag.addView(view2);

        TextView textView2 =new TextView(getActivity());
        LinearLayout.LayoutParams layoutParams5= new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams5.leftMargin =Densityuitl.dip2px(getActivity(),10);
        layoutParams5.topMargin =Densityuitl.dip2px(getActivity(),10);
        textView2.setLayoutParams(layoutParams5);
        textView2.setTextColor(getResources().getColor(R.color.grey_b5));
        textView2.setTextSize(12);
        textView2.setText("口味:");
        layout_flow_tag.addView(textView2);

        FlowTagLayout flowTagLayout2 =new FlowTagLayout(getActivity());
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams6.leftMargin =Densityuitl.dip2px(getActivity(),10);
        flowTagLayout2.setLayoutParams(layoutParams6);
        flowTagLayout2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        List<String> list2 =new ArrayList<>();
        list2.add("白色");
        list2.add("白色");
        list2.add("黑色");
        CommodityFlowTabAdapter tagAdapter2 =new CommodityFlowTabAdapter(getActivity());
        flowTagLayout2.setAdapter(tagAdapter2);
        tagAdapter2.onlyAddAll(list2);
        layout_flow_tag.addView(flowTagLayout2);

        iv_commodity_close.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        goodsNum.setText(num+"");
        goodsNum.setSelection(goodsNum.getText().toString().length());
        goodsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.equals("0",s.toString())) {
                    goodsNum.setText("1");
                }else if (!TextUtils.isEmpty(s.toString())){
                    goodsNum.setSelection(s.toString().length());
                }
            }
        });
        tv_add_shopping_cat.setOnClickListener(this);
        mRegisterScroll.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_commodity_close:
                CommodityWholesaleListActivity  activity =(CommodityWholesaleListActivity)getActivity();
                activity.getDrawerClose();
                break;
            case R.id.add:
                num =Integer.parseInt(goodsNum.getText().toString());
                num++;
                goodsNum.setText(num+"");
                break;
            case R.id.sub:
                num =Integer.parseInt(goodsNum.getText().toString());
                num--;
                goodsNum.setText(num+"");
                break;
            case R.id.tv_add_shopping_cat:
                if (null!=closeDrawerLayoutListener) {
                    closeDrawerLayoutListener.onCloseDrawerListener();
                }
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        layout_flow_tag.removeAllViews();
    }

    @Override
    public void onGlobalLayout() {
        //比较根布局与当前布局的大小
        int heightDiff = mRegisterScroll.getRootView().getHeight()- mRegisterScroll.getHeight();
        if(heightDiff >300){
            tv_add_shopping_cat.setVisibility(View.GONE);
        }else{
            //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (TextUtils.isEmpty(goodsNum.getText().toString())){
                        goodsNum.setText("1");
                    }
                    tv_add_shopping_cat.setVisibility(View.VISIBLE);
                }
            },100);

        }
    }
    @Override
    public void onDestroyView() {
        mRegisterScroll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        super.onDestroyView();
    }
    public interface CloseDrawerLayoutListener{
        void onCloseDrawerListener();
    }
    private CommodityScreenFragment.CloseDrawerLayoutListener closeDrawerLayoutListener;
    public void setCloseDrawerLayoutListener(CommodityScreenFragment.CloseDrawerLayoutListener closeDrawerLayoutListener){
        this.closeDrawerLayoutListener =closeDrawerLayoutListener;
    }
}
