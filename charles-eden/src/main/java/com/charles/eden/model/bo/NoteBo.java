package com.charles.eden.model.bo;

import java.util.Date;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:07
 */
public class NoteBo {

    private Long id;
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

    /** 用户id */
    private Long userId;
    /** 昵称 */
    private String nickname;
    /** 头像 */
    private String portrait;

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

    public Date getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(Date executeAt) {
        this.executeAt = executeAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
