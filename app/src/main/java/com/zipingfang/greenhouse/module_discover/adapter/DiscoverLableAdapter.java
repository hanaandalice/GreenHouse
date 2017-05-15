package com.zipingfang.greenhouse.module_discover.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shuyu.frescoutil.FrescoHelper;
import com.xilada.xldutils.tool.Densityuitl;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_discover.model.DiscoverLableModel;

import jp.wasabeef.fresco.processors.BlurPostprocessor;
import lib.lhh.fiv.library.FrescoImageView;

/**
 * Created by Administrator on 2017/5/5.
 */

public class DiscoverLableAdapter  extends BaseQuickAdapter<DiscoverLableModel,BaseViewHolder> {
    private Context context;
    public DiscoverLableAdapter(Context context) {
        super(R.layout.item_discover_label);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DiscoverLableModel item, int position) {

      if (position==0){
          helper.setText(R.id.tv_name,"领优惠券");
          Glide.with(context).load(R.mipmap.test_icon_discover_01).into(helper.<ImageView>getView(R.id.image_item));
      }else if (position==1){
          helper.setText(R.id.tv_name,"充值更省");
          Glide.with(context).load(R.mipmap.test_icon_discover_02).into(helper.<ImageView>getView(R.id.image_item));
      }else if (position==2){
          helper.setText(R.id.tv_name,"摇一摇");
          Glide.with(context).load(R.mipmap.test_icon_discover_03).into(helper.<ImageView>getView(R.id.image_item));
      }else if (position==3){
          helper.setText(R.id.tv_name,"幸运抽奖");
          Glide.with(context).load(R.mipmap.test_icon_discover_04).into(helper.<ImageView>getView(R.id.image_item));
      }
    }
}