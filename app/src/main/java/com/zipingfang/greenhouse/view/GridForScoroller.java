package com.zipingfang.greenhouse.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/4/5.
 */
public class GridForScoroller extends GridView {
    public GridForScoroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public GridForScoroller(Context context) {
        super(context);
    }
    public static final int VERTICAL_SPACING_DP = 15;
    public static final int HORIZONTAL_SPACING_DP = 15;
    public static final int NUM_COLUMNS = 3;
    public static final int PADDING = 15;

    public GridForScoroller(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //    public GridForScoroller(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setVerticalSpacing(DensityUtil.dip2px(context, VERTICAL_SPACING_DP));
//        setHorizontalSpacing(DensityUtil.dip2px(context, HORIZONTAL_SPACING_DP));
//        setNumColumns(NUM_COLUMNS);
//        setPadding(DensityUtil.dip2px(context, PADDING), 0, DensityUtil.dip2px(context, PADDING), 0);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
