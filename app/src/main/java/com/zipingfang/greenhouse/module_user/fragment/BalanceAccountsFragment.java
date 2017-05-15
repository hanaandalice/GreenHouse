package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.adapter.BalanceAccountsAdapter;
import com.zipingfang.greenhouse.module_user.model.BalanceAccountsModel;
import com.zipingfang.greenhouse.view.MyRecyclerDetorration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class BalanceAccountsFragment extends BaseLazyFragment {

    private RecyclerView recyclerView;
    private BalanceAccountsAdapter balanceAccountsAdapter;
    private String type;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_balance_accounts;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle =getArguments();
        type =bundle.getString("type");

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new MyRecyclerDetorration(getActivity()
                , LinearLayoutManager.VERTICAL
                , R.drawable.divider_line));

        balanceAccountsAdapter =new BalanceAccountsAdapter(getActivity());
        recyclerView.setAdapter(balanceAccountsAdapter);
        List<BalanceAccountsModel> balanceAccountsModelList =new ArrayList<>();
        balanceAccountsModelList.add(new BalanceAccountsModel());
        balanceAccountsModelList.add(new BalanceAccountsModel());
        balanceAccountsModelList.add(new BalanceAccountsModel());
        balanceAccountsAdapter.addData(balanceAccountsModelList);
    }

    @Override
    protected void onFirstVisibleToUser() {

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
}
