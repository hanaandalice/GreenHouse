package com.xilada.xldutils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.xilada.xldutils.utils.SharedPreferencesUtils;

import java.io.File;

/**
 * 必须先init();建议书写在application里面。
 * Created by LiaoXiang on 2015/11/18.
 */
public class xldUtils {
    public static String PICDIR ;
    public static Context mContext;//全局上下文
    public static String SPNAME;
    public static boolean DEBUG = true;

    public static void init(Context context,String spName){
        mContext = context;
        SPNAME = spName;
        initFilePath();
//        Fresco.initialize(context);
        SharedPreferencesUtils.init(context);
//        BitmapUtils.init();
    }

    /**
     * 创建文件路径
     */
    public static void initFilePath() {
        if (TextUtils.isEmpty(PICDIR)){
            if (mContext.getExternalCacheDir() == null){
                xldUtils.PICDIR = Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/Android/data/"+mContext.getPackageName()+"/cache/";
            }else {
                xldUtils.PICDIR = mContext.getExternalCacheDir().getAbsolutePath();
            }
        }
        File file = new File(PICDIR);
        if (!file.exists()){
            file.mkdirs();
        }
        file = null;
    }
}
