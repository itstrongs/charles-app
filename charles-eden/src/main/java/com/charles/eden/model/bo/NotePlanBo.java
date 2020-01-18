package com.charles.eden.model.bo;

import java.io.Serializable;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 16:18
 */
public class NotePlanBo implements Serializable {

    private Long id;

    /**
     * 笔记名
     */
    private String name;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 是否置顶
     */
    private Boolean isSetTop;

    /**
     * 笔记分类id
     */
    private Long typeId;

    /**
     * 笔记状态：0.未完成 1.完成
     */
    private Integer state;

    /**
     * 执行时间
     */
    private String executeAt;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 更新时间
     */
    private String updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSetTop() {
        return isSetTop;
    }

    public void setSetTop(Boolean setTop) {
        isSetTop = setTop;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(String executeAt) {
        this.executeAt = executeAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
