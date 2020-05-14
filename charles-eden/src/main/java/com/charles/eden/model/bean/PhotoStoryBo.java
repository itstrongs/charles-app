package com.charles.eden.model.bean;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2020-01-24 16:18
 */
public class PhotoStoryBo {

    private Long id;
    /** 用户id */
    private Long userId;
    /** 照片URL */
    private String photoUrl;
    /** 感想 */
    private String impression;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }
}
