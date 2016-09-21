package com.zhi.www.entry;

/**
 * Created by Administrator on 2016/9/16.
 */
public class DataInfo {

    public static final int DATA_RECEIVED = 1;
    public static final int DATA_SEND = 2;

    private String text;
    private int type;
    private String date;

    public DataInfo(String text, int type, String date){
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
