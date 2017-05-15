package com.zipingfang.greenhouse.module_home.model;

/**
 * Created by Administrator on 2017/5/7.
 */

public class MainAllModel {
    private int test_iv;
    private String test_name;

    public MainAllModel(int test_iv,String test_name){
        this.test_iv =test_iv;
        this.test_name =test_name;
    }
    public int getTest_iv() {
        return test_iv;
    }

    public String getTest_name() {
        return test_name;
    }
}
