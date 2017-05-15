package com.zipingfang.greenhouse.module_user.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.xilada.xldutils.activitys.TitleBarActivity;
import com.xilada.xldutils.utils.Toast;
import com.zipingfang.greenhouse.R;

import org.json.JSONException;

/**
 *
 * Created by Administrator on 2017/4/28.
 */

public class ScanCodeActivity extends TitleBarActivity {

    @Override
    protected int setContentLayout() {
        return R.layout.activity_sacn_code;
    }
    @Override
    protected void initView() throws JSONException, IllegalAccessException {
        setTitle("二维码扫描");
        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_qr_code_camera);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();
        captureFragment.setAnalyzeCallback(analyzeCallback);
    }
    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Toast.create(ScanCodeActivity.this).show(""+result);
            finish();
        }
        @Override
        public void onAnalyzeFailed() {
            Toast.create(ScanCodeActivity.this).show("二维码扫描出错");
        }
    };
}
