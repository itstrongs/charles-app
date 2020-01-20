package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月31日 10:40
 */
public class LoansProductBo {
    /**
     * pageNo : 1
     * pageSize : 10
     * resultList : [{"id":392,"createdAt":"2019-08-31T02:04:28.000+0000","updatedAt":null,"deletedAt":null,"deleteFlg":0,"name":"借东风2","minAmount":3000,"maxAmount":10000,"rate":"0.83%/月","duration":"2小时","deadline":"1月-12月","logoUrl":null,"url":null,"tag":"1,2,3","type":"0,1,2,3"},{"id":378,"createdAt":null,"updatedAt":null,"deletedAt":null,"deleteFlg":0,"name":"借东风1","minAmount":3000,"maxAmount":10000,"rate":"0.83%/月","duration":"2小时","deadline":"1月-12月","logoUrl":null,"url":null,"tag":null,"type":"0"}]
     */

    private int pageNo;
    private int pageSize;
    private List<ResultListBean> resultList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        /**
         * id : 392
         * createdAt : 2019-08-31T02:04:28.000+0000
         * updatedAt : null
         * deletedAt : null
         * deleteFlg : 0
         * name : 借东风2
         * minAmount : 3000
         * maxAmount : 10000
         * rate : 0.83%/月
         * duration : 2小时
         * deadline : 1月-12月
         * logoUrl : null
         * url : null
         * tag : 1,2,3
         * type : 0,1,2,3
         */

        private int id;
        private String createdAt;
        private Object updatedAt;
        private Object deletedAt;
        private int deleteFlg;
        private String name;
        private int minAmount;
        private int maxAmount;
        private String rate;
        private String duration;
        private String deadline;
        private String logoUrl;
        private String url;
        private String tag;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public int getDeleteFlg() {
            return deleteFlg;
        }

        public void setDeleteFlg(int deleteFlg) {
            this.deleteFlg = deleteFlg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMinAmount() {
            return minAmount;
        }

        public void setMinAmount(int minAmount) {
            this.minAmount = minAmount;
        }

        public int getMaxAmount() {
            return maxAmount;
        }

        public void setMaxAmount(int maxAmount) {
            this.maxAmount = maxAmount;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDeadline() {
            return deadline;
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
