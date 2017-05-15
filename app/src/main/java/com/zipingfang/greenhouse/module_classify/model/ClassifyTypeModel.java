package com.zipingfang.greenhouse.module_classify.model;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ClassifyTypeModel  {
    private String type;
    private boolean checked;

    public ClassifyTypeModel(String type,Boolean checked){
        this.checked =checked;
        this.type =type;
    }
    public String getType() {
        return type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setType(String type) {
        this.type = type;
    }
}
