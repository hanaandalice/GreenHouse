package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.MyEvaluationAdapter;
import com.zipingfang.greenhouse.module_user.model.MyEvaluationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyEvaluationFragment extends BaseLazyFragment {
    private RecyclerView recyclerView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_my_evaluation;
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
        MyEvaluationAdapter myEvaluationAdapter =new MyEvaluationAdapter(getActivity());
        recyclerView.setAdapter(myEvaluationAdapter);
        List<MyEvaluationModel> myEvaluationModelList =new ArrayList<>();
        myEvaluationModelList.add(new MyEvaluationModel());
        myEvaluationModelList.add(new MyEvaluationModel());
        myEvaluationModelList.add(new MyEvaluationModel());
        myEvaluationAdapter.addData(myEvaluationModelList);

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
}
