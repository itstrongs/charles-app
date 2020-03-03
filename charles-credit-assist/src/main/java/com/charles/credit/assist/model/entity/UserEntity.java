package com.charles.credit.assist.model.entity;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/6/4
 */
public class UserEntity {

    private Long userId;

    private String phone;

    private String nickname;

    private boolean isVip;

    private String authorization;

    private String wechatCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
