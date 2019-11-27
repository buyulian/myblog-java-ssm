package com.me.ssm.model;

import java.util.Date;
import java.util.List;

/**
 * Created by 不语恋 on 2017/5/12.
 */
public class Text {
    private int id;
    private String title;
    private String content;
    private Date date;

    private List<Integer> ids;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
