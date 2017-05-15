package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import com.xilada.xldutils.fragment.BaseFragment;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.activity.EvaluatedCenterActivity;
import com.zipingfang.greenhouse.module_user.activity.MyWalletActivity;
import com.zipingfang.greenhouse.module_user.activity.TheVipClubActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MyBullPostionFragment extends BaseFragment implements View.OnClickListener{
    private LinearLayout layout_my_wallet,
            layout_the_vip_club;
    private TextView tv_remain_to_be_evaluated;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null) {
            view =inflater.inflate(R.layout.fragment_my_bull_postion, null,false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout_my_wallet = (LinearLayout) view.findViewById(R.id.layout_my_wallet);
        layout_the_vip_club = (LinearLayout) view.findViewById(R.id.layout_the_vip_club);
        tv_remain_to_be_evaluated = (TextView) view.findViewById(R.id.tv_remain_to_be_evaluated);
        layout_my_wallet.setOnClickListener(this);
        layout_the_vip_club.setOnClickListener(this);
        tv_remain_to_be_evaluated.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_my_wallet:
                goActivity(MyWalletActivity.class);
                break;
            case R.id.layout_the_vip_club:
                goActivity(TheVipClubActivity.class);
                break;
            case R.id.tv_remain_to_be_evaluated:
                goActivity(EvaluatedCenterActivity.class);
                break;
        }
    }
}
