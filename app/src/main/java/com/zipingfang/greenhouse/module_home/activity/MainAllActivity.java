package com.zipingfang.greenhouse.module_home.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.adapter.MainAllAdapter;
import com.zipingfang.greenhouse.module_home.model.MainAllModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/7.
 */

public class MainAllActivity extends TitleBarActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    @Override
    protected int setContentLayout() {
        return R.layout.activity_main_all;
    }

    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("全部");
        showTitleBarLine(true);
        unbinder = ButterKnife.bind(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        List<MainAllModel> mainAllModelList =new ArrayList<>();
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_1,"我的收藏"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_2,"订单跟踪"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_3,"餐厅专供"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_4,"团  购"));

        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_5,"TOP爆款"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_6,"韩国美食"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_7,"领绿豆"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_8,"限时抢购"));

        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_9,"无线专享"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_10,"品牌闪购"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_11,"特价清仓"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_12,"新品预售"));

        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_13,"好买头条"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_14,"领优惠券"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_15,"会员中心"));
        mainAllModelList.add(new MainAllModel(R.mipmap.test_icon_16,"购卡更省"));

        MainAllAdapter mainAllAdapter =new MainAllAdapter(mainAllModelList,this);
        recyclerView.setAdapter(mainAllAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
