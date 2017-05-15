package com.zipingfang.greenhouse.module_user.model;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexBean;
import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

/**
 * Created by Administrator on 2017/5/12.
 */

public class MyFootprintModel implements ISuspensionInterface {
    private String baseIndexTag;//所属的分类（城市的汉语拼音首字母）

    public String getBaseIndexTag() {
        return baseIndexTag;
    }

    public MyFootprintModel setBaseIndexTag(String baseIndexTag) {
        this.baseIndexTag = baseIndexTag;
        return this;
    }

    @Override
    public String getSuspensionTag() {
        return baseIndexTag;
    }

    @Override
    public boolean isShowSuspension() {
        return true;
    }
}
