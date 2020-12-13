package com.charles.eden.model.bean;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 14:32
 */
public class MicroSentence {

    private Long id;
    /** 用户id */
    private Long userId;
    /** 分类 */
    private String category;
    /** 来源出处 */
    private String source;
    /** 内容 */
    private String content;
    /** 感受 */
    private String feel;
    /** 背景图地址 */
    private String imageUrl;
    /** 是否点赞 */
    private Boolean isFavour;
    /** 点赞人数 */
    private Integer favourNum;
    /** 评论数 */
    private Integer commentNum = 0;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getFavour() {
        return isFavour;
    }

    public void setFavour(Boolean favour) {
        isFavour = favour;
    }

    public Integer getFavourNum() {
        return favourNum;
    }

    public void setFavourNum(Integer favourNum) {
        this.favourNum = favourNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
}
