package com.zipingfang.greenhouse.module_home.model;

/**
 * Created by Administrator on 2017/5/2.
 */

public class CheckedModel {
    private String content;
    private boolean checked;
    public CheckedModel(String content,Boolean checked){
        this.content =content;
        this.checked =checked;
    }
    public String getContent() {
        return content;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }
}
