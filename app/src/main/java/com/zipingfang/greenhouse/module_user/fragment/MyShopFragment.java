package com.zipingfang.greenhouse.module_user.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xilada.xldutils.fragment.BaseFragment;
import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_user.activity.ScanCodeActivity;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MyShopFragment extends BaseFragment implements View.OnClickListener{
  private View view;
    private TextView tv_sacn_code;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view==null) {
            view =inflater.inflate(R.layout.fragment_my_shop, null,false);
            bindID(view);
        }
        return view;
    }

    private void bindID(View view) {
        tv_sacn_code = (TextView) view.findViewById(R.id.tv_sacn_code);
        tv_sacn_code.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sacn_code:
                goActivity(ScanCodeActivity.class);
                break;
        }
    }
}
