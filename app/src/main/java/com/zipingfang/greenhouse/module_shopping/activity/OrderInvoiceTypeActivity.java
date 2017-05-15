package com.zipingfang.greenhouse.module_shopping.activity;

import android.view.View;
import android.widget.AdapterView;

import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.view.ListForScoroller;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.adapter.OrderInvoiceGridAdapter;
import com.zipingfang.greenhouse.module_shopping.model.OrderInvoiceGridModel;
import com.zipingfang.greenhouse.view.GridForScoroller;
import com.zipingfang.greenhouse.view.XEditText;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/9.
 */

public class OrderInvoiceTypeActivity extends TitleBarActivity {

    @BindView(R.id.gridview_dispatching_type)
    GridForScoroller gridview_dispatching_type;
    @BindView(R.id.gridview_title_type)
    GridForScoroller gridview_title_type;
    @BindView(R.id.listview_invoice_contents)
    ListForScoroller listview_invoice_contents;
    @BindView(R.id.xEditText)
    XEditText xEditText;

    private Unbinder unbinder;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_order_invoice_type;
    }
    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("设置开票信息");
        setRightButtonText("确定");
        setRightButtonTextColor(getResources().getColor(R.color.redColor_f2));
        unbinder = ButterKnife.bind(this);

        xEditText.setDrawableRightListener(new XEditText.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                xEditText.setText("");
            }
        });
        final List<OrderInvoiceGridModel> orderInvoiceGridModelList =new ArrayList<>();
        orderInvoiceGridModelList.add(new OrderInvoiceGridModel("纸质发票"));
        orderInvoiceGridModelList.add(new OrderInvoiceGridModel("电子发票"));
        orderInvoiceGridModelList.add(new OrderInvoiceGridModel("增值税发票"));
        final OrderInvoiceGridAdapter orderInvoiceGridAdapter =new OrderInvoiceGridAdapter(this
                ,orderInvoiceGridModelList
                ,-1
                ,R.layout.item_order_invoice_grid);
        gridview_dispatching_type.setAdapter(orderInvoiceGridAdapter);
        gridview_dispatching_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderInvoiceGridAdapter.setSelectPosition(position);
            }
        });

        List<OrderInvoiceGridModel> orderInvoiceGridModelList2 =new ArrayList<>();
        orderInvoiceGridModelList2.add(new OrderInvoiceGridModel("个    人"));
        orderInvoiceGridModelList2.add(new OrderInvoiceGridModel("单    位"));
        final OrderInvoiceGridAdapter orderInvoiceGridAdapter2 =new OrderInvoiceGridAdapter(this
                ,orderInvoiceGridModelList2
                ,0
                ,R.layout.item_order_invoice_grid);
        gridview_title_type.setAdapter(orderInvoiceGridAdapter2);
        gridview_title_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderInvoiceGridAdapter2.setSelectPosition(position);
                if (position ==0){
                    xEditText.setHint("请输入个人发票的名称");
                }else if (position==1){
                    xEditText.setHint("请输入单位发票的名称");
                }
            }
        });

        List<OrderInvoiceGridModel> orderInvoiceGridModelList3 =new ArrayList<>();
        orderInvoiceGridModelList3.add(new OrderInvoiceGridModel("明    细"));
        orderInvoiceGridModelList3.add(new OrderInvoiceGridModel("食品（附商品清单）"));
        final OrderInvoiceGridAdapter orderInvoiceGridAdapter3 =new OrderInvoiceGridAdapter(this
                ,orderInvoiceGridModelList3
                ,-1
                ,R.layout.item_order_invoice_list);
        listview_invoice_contents.setAdapter(orderInvoiceGridAdapter3);
        listview_invoice_contents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                orderInvoiceGridAdapter3.setSelectPosition(position);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=unbinder) {
            unbinder.unbind();
        }
    }
}
