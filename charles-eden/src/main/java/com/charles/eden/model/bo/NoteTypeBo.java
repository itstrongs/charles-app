package com.charles.eden.model.bo;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-24 13:12
 */
public class NoteTypeBo {

    private Long id;
    /** 用户id */
    private Long userId;
    /** 分类名 */
    private String name;
    /** 描述 */
    private String description;
    /** 权限 */
    private Integer permission;
    /** 是否置顶 */
    private Boolean isSetTop;
    /** 类型 0.笔记 1.todo */
    private Integer type;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
