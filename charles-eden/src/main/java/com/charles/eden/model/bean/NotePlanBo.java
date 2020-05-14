package com.charles.eden.model.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 16:18
 */
public class NotePlanBo implements Serializable {

    private Long id;
    /** 用户id */
    private Long userId;
    /** 笔记名 */
    private String name;
    /** 笔记内容 */
    private String content;
    /** 是否置顶 */
    private Boolean isSetTop;
    /** 笔记分类id */
    private Long typeId;
    /** 笔记状态：0.未完成 1.完成 */
    private Integer state;
    /** 执行时间 */
    private Date executeAt;
    /** 权限 0.公开 1.自己可见 2.匿名发表 */
    private Integer permission;
    private String nickname;
    /** 更新时间 */
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Date executeAt) {
        this.executeAt = executeAt;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
