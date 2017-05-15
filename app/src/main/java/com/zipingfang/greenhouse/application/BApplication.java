package com.zipingfang.greenhouse.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.bugly.crashreport.CrashReport;
import com.xilada.xldutils.BaseApplication;

/**
 * Created by Administrator on 2017/4/26.
 */

public class BApplication extends BaseApplication {

    public static final String SP_NAME = "com.zipingfang.greenhouse";
    @Override
    protected String setSharedPreferencesName() {
        return SP_NAME;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //建议在测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "f5b40ad493", true);
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
