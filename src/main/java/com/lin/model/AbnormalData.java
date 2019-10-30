package com.lin.model;

import java.util.List;

/**
 * 异常数据
 */
public class AbnormalData {

    private String caption; //标题

    private List<String> title; //表头

    private List<List<Object>> data; //异常数据

    /** setter/getter **/

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AbnormalData{" +
                "caption='" + caption + '\'' +
                ", title=" + title +
                ", data=" + data +
                '}';
    }

}
