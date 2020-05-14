package com.charles.eden.model.bean;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-24 19:32
 */
public class TodoPlanBo {

    private Long id;
    /** 内容 */
    private String content;
    /** 类型id */
    private Long typeId;
    /** 笔记状态：0.未完成 1.完成 */
    private Boolean isFinish;
    /** 是否置顶 */
    private Boolean isSetTop;
    /** 用户id */
    private Long userId;
    /** 更新时间 */
    private String updatedAt;
    /** 创建时间 */
    private String createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }

    public Boolean getSetTop() {
        return isSetTop;
    }

    public void setSetTop(Boolean setTop) {
        isSetTop = setTop;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
