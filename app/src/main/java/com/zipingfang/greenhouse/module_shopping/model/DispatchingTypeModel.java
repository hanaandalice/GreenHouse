package com.zipingfang.greenhouse.module_shopping.model;

/**
 * Created by Administrator on 2017/5/9.
 */

public class DispatchingTypeModel {

    public static class DispatchingTypeCommodity{

    }
    public static class DispatchingTypedDispatching{
        private String contents;
        public  DispatchingTypedDispatching(String contents){
            this.contents =contents;
        }

        public String getContents() {
            return contents;
        }

    }
}
