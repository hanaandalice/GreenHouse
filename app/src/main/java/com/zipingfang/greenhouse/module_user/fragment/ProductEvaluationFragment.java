package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.ProductEvaluationAdapter;
import com.zipingfang.greenhouse.module_user.model.ProductEvaluationModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ProductEvaluationFragment extends BaseLazyFragment {
    private RecyclerView recyclerView;
    private ProductEvaluationAdapter productEvaluationAdapter;
    private String type;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_product_evaluation;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        type =bundle.getString("type");

    }

    @Override
    protected void onFirstVisibleToUser() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView =findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyRecyclerDetorration(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        productEvaluationAdapter =new ProductEvaluationAdapter(getActivity(),getActivity(),type);
        recyclerView.setAdapter(productEvaluationAdapter);

        List<ProductEvaluationModel> productEvaluationModelList =new ArrayList<>();
        productEvaluationModelList.add(new ProductEvaluationModel());
        productEvaluationModelList.add(new ProductEvaluationModel());
        productEvaluationAdapter.addData(productEvaluationModelList);

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
}
