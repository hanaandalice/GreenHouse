package com.zipingfang.greenhouse.module_home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xilada.xldutils.fragment.BaseLazyFragment;
import com.zipingfang.greenhouse.R;
import com.zipingfang.greenhouse.module_home.adapter.GoodsAttrListAdapter;
import com.zipingfang.greenhouse.module_home.adapter.GoodsAttrsAdapter;
import com.zipingfang.greenhouse.module_home.model.GoodsAttrListModel;
import com.zipingfang.greenhouse.module_home.model.SaleAttributeVo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CommodityScreenFragment extends BaseLazyFragment implements ViewTreeObserver.OnGlobalLayoutListener{
    private ListView selection_list;
    private GoodsAttrListAdapter adapter;
    private GoodsAttrsAdapter serviceAdapter;
    private List<GoodsAttrListModel> itemData;
    private List<SaleAttributeVo> serviceList;
    private TextView tv_resetting,
            tv_confirm;
    private RelativeLayout mRegisterScroll;
    private LinearLayout layout_bottom;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_commodity_screen;
    }

    @Override
    protected void onFirstVisibleToUser() {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selection_list =findViewById(R.id.selection_list);
        tv_resetting =findViewById(R.id.tv_resetting);
        tv_confirm =findViewById(R.id.tv_confirm);
        mRegisterScroll =findViewById(R.id.mRegisterScroll);
        layout_bottom =findViewById(R.id.layout_bottom);

        itemData = new ArrayList<GoodsAttrListModel>();
        adapter = new GoodsAttrListAdapter(context, itemData);
        selection_list.setAdapter(adapter);
        String str = "["
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"http\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"http\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"http\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"http\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"商铺\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"2核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"4核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"6核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"8核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"CPU\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"全网通\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"移动4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"电信4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"联通4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"网络制式\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"OPPO\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"荣耀\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"苹果\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"鸭梨\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"月饼\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"vivo\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"品牌\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"音乐\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"拍照\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"待机长\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"主打\"},"
                + "{\"nameId\":\"V2QLAH\",\"saleVo\":["
                + "{\"value\":\"4.5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"5.5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"6英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"}"
                + "],\"name\":\"尺寸\"}" + "]";
        JSONArray json = null;
        try {
            json = new JSONArray(str);
            refreshAttrs(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 重置的点击监听，将所有选项全设为false
        tv_resetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        itemData.get(i).getSaleVo().get(j).setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        // 确定的点击监听，将所有已选中项列出
        tv_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = "";
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                            str = str + itemData.get(i).getSaleVo().get(j).getValue();
                        }
                    }
                }
                if (null!=closeDrawerLayoutListener) {
                    closeDrawerLayoutListener.onCloseDrawerListener();
                }
            }
        });
        mRegisterScroll.getViewTreeObserver().addOnGlobalLayoutListener(this);

    }
    /**
     * 刷新商品属性
     *
     * @param json
     * @throws JSONException
     */
    public void refreshAttrs(JSONArray json) throws JSONException {
        itemData.clear();
        for (int i = 0; i < json.length(); i++) {
            GoodsAttrListModel saleName = new GoodsAttrListModel();
            JSONObject obj = (JSONObject) json.opt(i);
            saleName.setName(obj.getString("name"));
            List<SaleAttributeVo> list = new ArrayList<SaleAttributeVo>();
            JSONArray array = new JSONArray(obj.getString("saleVo"));
            for (int j = 0; j < array.length(); j++) {
                JSONObject object = array.getJSONObject(j);
                SaleAttributeVo vo = new SaleAttributeVo();
                vo.setGoods(object.getString("goods"));
                vo.setValue(object.getString("value"));
                vo.setGoodsAndValId(object.getString("goodsAndValId"));
                if ("1".equals(object.getString("checkStatus"))) {
                    vo.setChecked(true);
                } else {
                    vo.setChecked(false);
                }
                list.add(vo);
            }
            saleName.setSaleVo(list);
            // 是否展开
            saleName.setNameIsChecked(false);
            itemData.add(saleName);
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }

    @Override
    public void onGlobalLayout() {
        //比较根布局与当前布局的大小
        int heightDiff = mRegisterScroll.getRootView().getHeight()- mRegisterScroll.getHeight();
        if(heightDiff >300){
            layout_bottom.setVisibility(View.GONE);
        }else{
            //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    layout_bottom.setVisibility(View.VISIBLE);
                }
            },200);

        }
    }
    public interface CloseDrawerLayoutListener{
        void onCloseDrawerListener();
    }
    private CloseDrawerLayoutListener closeDrawerLayoutListener;
    public void setCloseDrawerLayoutListener(CloseDrawerLayoutListener closeDrawerLayoutListener){
        this.closeDrawerLayoutListener =closeDrawerLayoutListener;
    }
}
