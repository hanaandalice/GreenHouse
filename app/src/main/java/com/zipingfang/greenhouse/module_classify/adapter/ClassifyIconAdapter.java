package com.zipingfang.greenhouse.module_classify.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseQuickAdapter;
import com.zipingfang.greenhouse.JD_module.base.baseadapter.BaseViewHolder;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_classify.model.ClassifyIconLabelModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyIconModel;
import com.zipingfang.greenhouse.module_classify.model.ClassifyLogoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ClassifyIconAdapter extends BaseQuickAdapter<ClassifyIconModel,BaseViewHolder> {

    private Context context;
    public ClassifyIconAdapter(Context context) {
        super(R.layout.item_classify_icon);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassifyIconModel item, int position) {
        ClassifyIconLabelAdapter iconLabelAdapter =new ClassifyIconLabelAdapter(context);
        helper.<RecyclerView>getView(R.id.recycler_icon).setLayoutManager(new GridLayoutManager(context,3));
        helper.<RecyclerView>getView(R.id.recycler_icon).setAdapter(iconLabelAdapter);
        iconLabelAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        List<ClassifyIconLabelModel> iconLabelModelList =new ArrayList<>();
        iconLabelModelList.add(new ClassifyIconLabelModel());
        iconLabelModelList.add(new ClassifyIconLabelModel());
        iconLabelModelList.add(new ClassifyIconLabelModel());
        iconLabelModelList.add(new ClassifyIconLabelModel());
        iconLabelModelList.add(new ClassifyIconLabelModel());
        iconLabelAdapter.addData(iconLabelModelList);
    }
}
