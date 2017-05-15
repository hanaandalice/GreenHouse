package com.zipingfang.greenhouse.module_shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.animation.FlipEnter.FlipVerticalEnter;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.dialog.CollarCouponsDialog;
import com.zipingfang.greenhouse.module_shopping.activity.FavorableCommodityActivity;
import com.zipingfang.greenhouse.module_shopping.dialog.RedemptionDialog;
import com.zipingfang.greenhouse.module_shopping.dialog.SpecificationDialog;
import com.zipingfang.greenhouse.module_shopping.model.GroupInfo;
import com.zipingfang.greenhouse.module_shopping.model.ProductInfo;
import com.zipingfang.greenhouse.view.shoppingCartExpandableList.SlideView;

import java.util.List;
import java.util.Map;


public class ShopcartExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<GroupInfo> groups;
    private Map<String, List<ProductInfo>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private boolean isEdit =false;
    private View layout_view;
    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public ShopcartExpandableListViewAdapter(List<GroupInfo> groups, Map<String, List<ProductInfo>> children, Context context,View layout_view) {
        super();
        this.groups = groups;
        this.children = children;
        this.context = context;
        this.layout_view =layout_view;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());

        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder gholder;
        if (convertView == null) {
            gholder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.layout_redemption = (LinearLayout) convertView.findViewById(R.id.layout_redemption);
            gholder.layout_led_the_securities = (LinearLayout) convertView.findViewById(R.id.layout_led_the_securities);
//            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_source_name);
            //groupMap.put(groupPosition, convertView);
            convertView.setTag(gholder);
        } else {
            // convertView = groupMap.get(groupPosition);
            gholder = (GroupHolder) convertView.getTag();
        }
        final GroupInfo group = (GroupInfo) getGroup(groupPosition);
        if (groupPosition==0){
            gholder.layout_redemption.setVisibility(View.VISIBLE);
            gholder.layout_led_the_securities.setVisibility(View.GONE);
        }else{
            gholder.layout_redemption.setVisibility(View.GONE);
            gholder.layout_led_the_securities.setVisibility(View.VISIBLE);
        }
        if (isEdit) {
            gholder.cb_check.setChecked(group.isEditChoosed());
        }else{
            gholder.cb_check.setChecked(group.isChoosed());
        }
        if (group != null) {
//            gholder.tv_group_name.setText(group.getName());
            gholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEdit){
                        group.setEditChoosed(((CheckBox) v).isChecked());
                    }else{
                        group.setChoosed(((CheckBox) v).isChecked());
                    }
                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
                }
            });

        }
        gholder.layout_redemption.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setClass(context, FavorableCommodityActivity.class);
                context.startActivity(intent);


            }
        });
        gholder.layout_led_the_securities.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final CollarCouponsDialog collarCouponsDialog = new CollarCouponsDialog(context);
            }
        });
        return convertView;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SlideView slideView = null;
        final ChildHolder cholder;
        if (convertView == null) {
            cholder = new ChildHolder();
            View view = View.inflate(context, R.layout.item_shopcart_product, null);
            slideView = new SlideView(context, context.getResources(), view);
            convertView = slideView;
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.check_box);
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            cholder.iv_increase = (TextView) convertView.findViewById(R.id.tv_add);
            cholder.iv_decrease = (TextView) convertView.findViewById(R.id.tv_reduce);
            cholder.tv_count = (EditText) convertView.findViewById(R.id.et_num);
            cholder.tv_standard = (TextView) convertView.findViewById(R.id.tv_standard);
            cholder.layout_selected_standard = (LinearLayout) convertView.findViewById(R.id.layout_selected_standard);
            cholder.tv_selected_standard = (TextView) convertView.findViewById(R.id.tv_selected_standard);
            cholder.layout_selected_specification = (LinearLayout) convertView.findViewById(R.id.layout_selected_specification);
            cholder.layout_visibility_specification = (LinearLayout) convertView.findViewById(R.id.layout_visibility_specification);

            cholder.tv_delete = (TextView) convertView.findViewById(R.id.back);
            // childrenMap.put(groupPosition, convertView);
            convertView.setTag(cholder);
        } else {
            // convertView = childrenMap.get(groupPosition);
            cholder = (ChildHolder) convertView.getTag();
        }
        final ProductInfo product = (ProductInfo) getChild(groupPosition, childPosition);
        if (isEdit){
            cholder.layout_selected_standard.setVisibility(View.VISIBLE);
            cholder.layout_selected_specification.setVisibility(View.VISIBLE);
            cholder.layout_visibility_specification.setVisibility(View.GONE);
            cholder.tv_standard.setVisibility(View.GONE);
            cholder.cb_check.setChecked(product.isEditChoosed());
        }else{
            cholder.layout_selected_standard.setVisibility(View.GONE);
            cholder.tv_standard.setVisibility(View.VISIBLE);
            cholder.layout_selected_specification.setVisibility(View.GONE);
            cholder.layout_visibility_specification.setVisibility(View.VISIBLE);
            cholder.cb_check.setChecked(product.isChoosed());
        }
        if (product != null) {
            cholder.tv_price.setText(product.getPrice() + "");
            cholder.tv_count.setText(product.getCount() + "");
            cholder.cb_check.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isEdit){
                        product.setEditChoosed(((CheckBox) v).isChecked());
                    }else{
                        product.setChoosed(((CheckBox) v).isChecked());
                    }
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.iv_increase.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                }
            });
            cholder.iv_decrease.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口
                }
            });
        }
        cholder.tv_delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                List<ProductInfo> childs = children.get(groups.get(groupPosition).getId());
                childs.remove(childPosition);
                if(childs.size() ==0){//child没有了，group也就没有了
                    groups.remove(groupPosition);
                }
                notifyDataSetChanged();
                modifyCountInterface.slideDelete();
            }
        });
        cholder.layout_selected_standard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final SpecificationDialog shareBottomDialog = new SpecificationDialog(context,layout_view);
                shareBottomDialog.showAnim(new FlipVerticalEnter())
                        .show();
//                cholder.tv_selected_standard
            }
        });
        cholder.layout_selected_specification.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final RedemptionDialog collarCouponsDialog = new RedemptionDialog(context);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 组元素绑定器
     */
    private class GroupHolder {
        CheckBox cb_check;
        TextView tv_group_name;
        LinearLayout layout_redemption,
                layout_led_the_securities;
    }

    /**
     * 子元素绑定器
     */
    private class ChildHolder {
        CheckBox cb_check;
        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        EditText tv_count;
        TextView iv_decrease;
        TextView tv_delete;
        TextView tv_standard;
        TextView tv_selected_standard;
        LinearLayout layout_selected_standard,
                layout_selected_specification,
                layout_visibility_specification;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        public void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        public void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);
        /**
         * 滑动删除
         */
        void slideDelete();
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }
}
