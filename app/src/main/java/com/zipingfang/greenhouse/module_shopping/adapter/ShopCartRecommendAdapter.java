package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xilada.xldutils.tool.Densityuitl;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.model.ShopCartRecommendModel;

/**
 * Created by Administrator on 2017/5/8.
 */

public class ShopCartRecommendAdapter extends BaseQuickAdapter<ShopCartRecommendModel,BaseViewHolder> {
    private Context context;

    public ShopCartRecommendAdapter(Context context) {
        super(R.layout.item_shopcart_recommend);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartRecommendModel item, int position) {


        helper.<LinearLayout>getView(R.id.table_group).removeAllViews();
        ImageView imageView =new ImageView(context);
        imageView.setImageResource(R.mipmap.icon_mobile_phone_exclusive);
        helper.<LinearLayout>getView(R.id.table_group).addView(imageView);
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams1.rightMargin = dip2px(context,6);
        imageView.setLayoutParams(layoutParams1);

        ImageView imageView2 =new ImageView(context);
        imageView2.setImageResource(R.mipmap.icon_table_discount_coupon);
        helper.<LinearLayout>getView(R.id.table_group).addView(imageView2);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView2.getLayoutParams();
        layoutParams2.rightMargin =dip2px(context,6);
        imageView2.setLayoutParams(layoutParams2);

        ImageView imageView3 =new ImageView(context);
        imageView3.setImageResource(R.mipmap.icon_table_give_as_a_present);
        helper.<LinearLayout>getView(R.id.table_group).addView(imageView3);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) imageView3.getLayoutParams();
        layoutParams3.rightMargin =dip2px(context,6);
        imageView3.setLayoutParams(layoutParams3);

        ImageView imageView4 =new ImageView(context);
        imageView4.setImageResource(R.mipmap.icon_table_mitigate_a_punishment);
        helper.<LinearLayout>getView(R.id.table_group).addView(imageView4);
        LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) imageView4.getLayoutParams();
        layoutParams4.rightMargin =dip2px(context,6);
        imageView4.setLayoutParams(layoutParams4);

        ImageView imageView5 =new ImageView(context);
        imageView5.setImageResource(R.mipmap.icon_table_group_buying);
        helper.<LinearLayout>getView(R.id.table_group).addView(imageView5);
        LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) imageView5.getLayoutParams();
        layoutParams5.rightMargin =dip2px(context,6);
        imageView5.setLayoutParams(layoutParams5);

    }
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
