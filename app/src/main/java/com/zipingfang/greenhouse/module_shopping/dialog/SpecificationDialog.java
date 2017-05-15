package com.zipingfang.greenhouse.module_shopping.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.flyco.animation.FlipEnter.FlipVerticalEnter;
import com.flyco.animation.FlipEnter.FlipVerticalSwingEnter;
import com.flyco.animation.FlipExit.FlipVerticalExit;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.xilada.xldutils.network.HttpUtils;
import com.xilada.xldutils.tool.Densityuitl;
import com.xilada.xldutils.utils.SharedPreferencesUtils;
import com.xilada.xldutils.view.flawtaglayout.FlowTagLayout;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.activity.CommodityWholesaleListActivity;
import com.zipingfang.greenhouse.module_home.adapter.CommodityFlowTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */

public class SpecificationDialog  extends BottomBaseDialog<SpecificationDialog> implements View.OnClickListener{
    private RelativeLayout layout_dismiss;
    private LinearLayout layout_flow_tag;
    private ImageView iv_commodity_close;
    private TextView add,
            sub,
            tv_add_shopping_cat;
    private EditText goodsNum;
    private int num=1;
    private Context context;
    public SpecificationDialog(Context context, View animateView) {
        super(context, animateView);
        this.context =context;
    }
    public SpecificationDialog(Context context) {
        super(context);
        this.context =context;
    }
    @Override
    public View onCreateView() {
//        showAnim(new FlipVerticalEnter());
//        dismissAnim(new FlipVerticalExit());
        showAnim(new FlipVerticalEnter());
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_specification, null);

        layout_dismiss = (RelativeLayout) inflate.findViewById(R.id.layout_dismiss);
        iv_commodity_close = (ImageView) inflate.findViewById(R.id.iv_commodity_close);
        layout_dismiss = (RelativeLayout) inflate.findViewById(R.id.layout_dismiss);
        layout_flow_tag = (LinearLayout) inflate.findViewById(R.id.layout_flow_tag);
        goodsNum = (EditText) inflate.findViewById(R.id.goodsNum);
        add = (TextView) inflate.findViewById(R.id.add);
        sub = (TextView) inflate.findViewById(R.id.sub);
        tv_add_shopping_cat = (TextView)inflate.findViewById(R.id.tv_add_shopping_cat);

        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        View view =new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Densityuitl.dip2px(context,0.5f));
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.grey_line));
        layout_flow_tag.addView(view);

        TextView textView =new TextView(context);
        LinearLayout.LayoutParams layoutParams2 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams2.leftMargin =Densityuitl.dip2px(context,10);
        layoutParams2.topMargin =Densityuitl.dip2px(context,10);
        textView.setLayoutParams(layoutParams2);
        textView.setTextColor(context.getResources().getColor(R.color.grey_b5));
        textView.setTextSize(12);
        textView.setText("规格:");
        layout_flow_tag.addView(textView);

        FlowTagLayout flowTagLayout =new FlowTagLayout(context);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams3.leftMargin =Densityuitl.dip2px(context,10);
        flowTagLayout.setLayoutParams(layoutParams3);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        List<String> list1 =new ArrayList<>();
        list1.add("550ML/1箱");
        list1.add("250ML/5箱");
        list1.add("450ML/6箱");
        CommodityFlowTabAdapter tagAdapter1 =new CommodityFlowTabAdapter(context);
        flowTagLayout.setAdapter(tagAdapter1);
        tagAdapter1.onlyAddAll(list1);
        layout_flow_tag.addView(flowTagLayout);

        View view2 =new View(context);
        LinearLayout.LayoutParams layoutParams4 =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                Densityuitl.dip2px(context,0.5f));
        layoutParams4.topMargin = Densityuitl.dip2px(context,10f);
        view2.setLayoutParams(layoutParams4);
        view2.setBackgroundColor(context.getResources().getColor(R.color.grey_line));
        layout_flow_tag.addView(view2);

        TextView textView2 =new TextView(context);
        LinearLayout.LayoutParams layoutParams5= new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams5.leftMargin =Densityuitl.dip2px(context,10);
        layoutParams5.topMargin =Densityuitl.dip2px(context,10);
        textView2.setLayoutParams(layoutParams5);
        textView2.setTextColor(context.getResources().getColor(R.color.grey_b5));
        textView2.setTextSize(12);
        textView2.setText("口味:");
        layout_flow_tag.addView(textView2);

        FlowTagLayout flowTagLayout2 =new FlowTagLayout(context);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams6.leftMargin =Densityuitl.dip2px(context,10);
        flowTagLayout2.setLayoutParams(layoutParams6);
        flowTagLayout2.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        List<String> list2 =new ArrayList<>();
        list2.add("白色");
        list2.add("白色");
        list2.add("黑色");
        CommodityFlowTabAdapter tagAdapter2 =new CommodityFlowTabAdapter(context);
        flowTagLayout2.setAdapter(tagAdapter2);
        tagAdapter2.onlyAddAll(list2);
        layout_flow_tag.addView(flowTagLayout2);

        iv_commodity_close.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        layout_dismiss.setOnClickListener(this);
        tv_add_shopping_cat.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_shopping_cat:
                dismiss();
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
            case R.id.layout_dismiss:
                dismiss();
                break;
            case R.id.iv_commodity_close:
                dismiss();
                break;
        }
    }
}
