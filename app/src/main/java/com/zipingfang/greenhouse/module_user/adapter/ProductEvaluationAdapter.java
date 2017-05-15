package com.zipingfang.greenhouse.module_user.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.model.CollectMyShopModel;
import com.zipingfang.greenhouse.module_user.model.ProductEvaluationModel;
import com.zipingfang.greenhouse.um.ShareBottomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ProductEvaluationAdapter  extends BaseQuickAdapter<ProductEvaluationModel,BaseViewHolder> {
    private Context context;
    private String type;
    private Activity activity;
    public ProductEvaluationAdapter(Context context,Activity activity,String type) {
        super(R.layout.item_product_evaluation);
        this.context = context;
        this.type =type;
        this.activity =activity;
    }
    @Override
    protected void convert(BaseViewHolder helper, ProductEvaluationModel item, int position) {
        if (TextUtils.equals("1",type)) {
            helper.getView(R.id.layout_add_evaluation).setVisibility(View.VISIBLE);
            helper.getView(R.id.recyclerView_img).setVisibility(View.GONE);
        }else{
            helper.getView(R.id.layout_add_evaluation).setVisibility(View.GONE);
            helper.getView(R.id.recyclerView_img).setVisibility(View.VISIBLE);
            helper.<RecyclerView>getView(R.id.recyclerView_img).setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            ChildImageAdapter childImageAdapter =new ChildImageAdapter();
            helper.<RecyclerView>getView(R.id.recyclerView_img).setAdapter(childImageAdapter);
            List<ProductEvaluationModel.EvaluationImageModel> evaluationImageModelList =new ArrayList<>();
            evaluationImageModelList.add(new ProductEvaluationModel.EvaluationImageModel());
            evaluationImageModelList.add(new ProductEvaluationModel.EvaluationImageModel());
            evaluationImageModelList.add(new ProductEvaluationModel.EvaluationImageModel());
            childImageAdapter.addData(evaluationImageModelList);
        }

        helper.getView(R.id.iv_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBottomDialog shareBottomDialog =new ShareBottomDialog(context,activity);
                shareBottomDialog.show();
            }
        });
    }
    class ChildImageAdapter extends BaseQuickAdapter<ProductEvaluationModel.EvaluationImageModel,BaseViewHolder> {
        public ChildImageAdapter() {
            super(R.layout.item_img);
        }
        @Override
        protected void convert(BaseViewHolder helper, ProductEvaluationModel.EvaluationImageModel item, int position) {

        }
    }
}
