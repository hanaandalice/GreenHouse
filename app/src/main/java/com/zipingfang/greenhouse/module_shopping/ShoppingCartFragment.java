package com.zipingfang.greenhouse.module_shopping;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_shopping.activity.OrderBalanceActivity;
import com.zipingfang.greenhouse.module_shopping.adapter.ShopCartRecommendAdapter;
import com.zipingfang.greenhouse.module_shopping.adapter.ShopcartExpandableListViewAdapter;
import com.zipingfang.greenhouse.module_shopping.model.GroupInfo;
import com.zipingfang.greenhouse.module_shopping.model.ProductInfo;
import com.zipingfang.greenhouse.module_shopping.model.ShopCartRecommendModel;
import com.zipingfang.greenhouse.view.GridSpacingItemDecoration;
import com.zipingfang.greenhouse.view.shoppingCartExpandableList.SuperExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2017/4/28.
 */
public class ShoppingCartFragment extends BaseLazyFragment  implements ShopcartExpandableListViewAdapter.CheckInterface, View.OnClickListener, ShopcartExpandableListViewAdapter.ModifyCountInterface{
    private SuperExpandableListView shopcart_exlist;
    private ShopcartExpandableListViewAdapter selva;
    private CheckBox check_box;
    private TextView tv_price,
            tv_delete_and_go_to_pay,
            tv_edit;
    private RecyclerView recyclerView;

    private LinearLayout layout_total,
            layout_delete,
            layout_shopcart_num,
            layout_view;

    private List<GroupInfo> groups = new ArrayList<GroupInfo>();// 组元素数据列表
    private Map<String, List<ProductInfo>> children = new HashMap<>();// 子元素数据列表
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private Boolean isEdit =false;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_shopping;
    }

    @Override
    protected void onFirstVisibleToUser() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        virtualData();
        shopcart_exlist =findViewById(R.id.shopcart_exlist);
        layout_view =findViewById(R.id.layout_view);
        layout_shopcart_num =findViewById(R.id.layout_shopcart_num);
        layout_total =findViewById(R.id.layout_total);
        layout_delete =findViewById(R.id.layout_delete);
        check_box =findViewById(R.id.check_box);
        tv_price =findViewById(R.id.tv_price);
        tv_edit =findViewById(R.id.tv_edit);
        tv_delete_and_go_to_pay =findViewById(R.id.tv_delete_and_go_to_pay);
        recyclerView =findViewById(R.id.recyclerView);
        GridSpacingItemDecoration layoutDecoration = new GridSpacingItemDecoration(2,20,false);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(layoutDecoration);

        ShopCartRecommendAdapter shopCartRecommendAdapter =new ShopCartRecommendAdapter(getActivity());
        recyclerView.setAdapter(shopCartRecommendAdapter);
        List<ShopCartRecommendModel> shopCartRecommendModelList =new ArrayList<>();
        shopCartRecommendModelList.add(new ShopCartRecommendModel());
        shopCartRecommendModelList.add(new ShopCartRecommendModel());
        shopCartRecommendModelList.add(new ShopCartRecommendModel());
        shopCartRecommendModelList.add(new ShopCartRecommendModel());
        shopCartRecommendModelList.add(new ShopCartRecommendModel());
        shopCartRecommendAdapter.addData(shopCartRecommendModelList);

        selva = new ShopcartExpandableListViewAdapter(groups, children, getActivity(),layout_view);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        shopcart_exlist.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            shopcart_exlist.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
        check_box.setOnClickListener(this);
        tv_delete_and_go_to_pay.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void virtualData() {
        for (int i = 0; i < 2; i++) {
            groups.add(new GroupInfo(i + "", "良品铺子" + (i + 1) + "号店"));
            List<ProductInfo> products = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                products.add(new ProductInfo(j + "", "商品", "", groups.get(i).getName() + "的第" + (j + 1) + "个商品", 120.00 + i * j, 1+j));
            }
            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
        }
    }
    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
    @Override
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.check_box:
                doCheckAll();
                break;
//            case R.id.tv_go_to_pay:
//                if (totalCount == 0) {
//                    Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                alert = new AlertDialog.Builder(context).create();
//                alert.setTitle("操作提示");
//                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元");
//                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogs, int which) {
//                        return;
//                    }
//                });
//                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialogs, int which) {
//                        return;
//                    }
//                });
//                alert.show();
//                break;
            case R.id.tv_delete_and_go_to_pay:
                if (isEdit){
                    if (totalCount == 0) {
                        Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                        return;
                    }
                    alert = new AlertDialog.Builder(context).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            doDelete();
                        }
                    });
                    alert.show();
                }else{
                    goActivity(OrderBalanceActivity.class);
                }
                break;
            case R.id.tv_edit://编辑
                if (!isEdit) {
                    tv_edit.setText("完成");
                    isEdit =true;
                    tv_delete_and_go_to_pay.setText("删除");
                    layout_total.setVisibility(View.GONE);
                    layout_delete.setVisibility(View.VISIBLE);

                }else{
                    isEdit =false;
                    tv_edit.setText("编辑");
                    tv_delete_and_go_to_pay.setText("去结算（"+totalCount+"）");
                    layout_total.setVisibility(View.VISIBLE);
                    layout_delete.setVisibility(View.GONE);
                }
                isAllChecked();
                selva.setEdit(isEdit);
                break;
        }
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (isEdit) {
                childs.get(i).setEditChoosed(isChecked);
            }else{
                childs.get(i).setChoosed(isChecked);
            }
        }
        isAllChecked();
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        GroupInfo group = groups.get(groupPosition);
        List<ProductInfo> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            if (isEdit){
                if (childs.get(i).isEditChoosed() != isChecked) {
                    allChildSameState = false;
                    break;
                }
            }else{
                if (childs.get(i).isChoosed() != isChecked) {
                    allChildSameState = false;
                    break;
                }
            }
        }
        if (isEdit) {
            if (allChildSameState) {
                group.setEditChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
            } else {
                group.setEditChoosed(false);// 否则，组元素一律设置为未选中状态
            }
        }else{
            if (allChildSameState) {
                group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
            } else {
                group.setChoosed(false);// 否则，组元素一律设置为未选中状态
            }
        }
        isAllChecked();
        selva.notifyDataSetChanged();
        calculate();
    }
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ProductInfo product = (ProductInfo) selva.getChild(groupPosition, childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");

        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void slideDelete() {
        if (groups.size()>0){
            layout_shopcart_num.setVisibility(View.GONE);
        }else{
            layout_shopcart_num.setVisibility(View.VISIBLE);
        }
    }

    /**
     *   删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    public void doDelete() {
        List<GroupInfo> toBeDeleteGroups = new ArrayList<GroupInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            if (isEdit) {
                if (group.isEditChoosed()) {
                    toBeDeleteGroups.add(group);
                }
            }else{
                if (group.isChoosed()) {
                    toBeDeleteGroups.add(group);
                }
            }
            List<ProductInfo> toBeDeleteProducts = new ArrayList<ProductInfo>();// 待删除的子元素列表
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (isEdit) {
                    if (childs.get(j).isEditChoosed()) {
                        toBeDeleteProducts.add(childs.get(j));
                    }
                }else{
                    if (childs.get(j).isChoosed()) {
                        toBeDeleteProducts.add(childs.get(j));
                    }
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        groups.removeAll(toBeDeleteGroups);
        selva.notifyDataSetChanged();
        calculate();
    }

    /** 全选与反选 */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            if (isEdit) {
                groups.get(i).setEditChoosed(check_box.isChecked());
            }else{
                groups.get(i).setChoosed(check_box.isChecked());
            }
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (isEdit) {
                    childs.get(j).setEditChoosed(check_box.isChecked());
                }else{
                    childs.get(j).setChoosed(check_box.isChecked());
                }
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }
    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            GroupInfo group = groups.get(i);
            List<ProductInfo> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                ProductInfo product = childs.get(j);
                if (isEdit) {
                    if (product.isEditChoosed()) {
                        totalCount++;
                        totalPrice += product.getPrice() * product.getCount();
                    }
                }else{
                    if (product.isChoosed()) {
                        totalCount++;
                        totalPrice += product.getPrice() * product.getCount();
                    }
                }
            }
        }
        if (!isEdit){
            tv_price.setText("¥ "+totalPrice);
            tv_delete_and_go_to_pay.setText("去结算（"+totalCount+"）");
        }
        if (groups.size()<=0){
            check_box.setChecked(false);
            layout_shopcart_num.setVisibility(View.VISIBLE);
        }else{
            layout_shopcart_num.setVisibility(View.GONE);
        }
    }
   private void isAllChecked(){
       if (groups.size()<=0){
           check_box.setChecked(false);
       }else{
           if (isEdit) {
               if (isAllEditCheck()) {
                   check_box.setChecked(true);
               }else{
                   check_box.setChecked(false);
               }
           }else{
               if (isAllCheck())
                   check_box.setChecked(true);
               else{
                   check_box.setChecked(false);
               }
           }
       }
    }
    private boolean isAllCheck() {
        for (GroupInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }
    private boolean isAllEditCheck() {
        for (GroupInfo group : groups) {
            if (!group.isEditChoosed())
                return false;
        }
        return true;
    }
}
