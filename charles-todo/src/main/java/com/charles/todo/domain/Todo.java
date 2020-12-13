package com.charles.todo.domain;

import java.util.Date;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-06-15 21:30
 */
public class Todo {

    private String content;
    private Date createdAt;

    public Todo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
