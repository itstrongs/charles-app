package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月01日 14:28
 */
public class QueryRecordBo {
    /**
     * pageNo : 1.0
     * pageSize : 10.0
     * resultList : [{"id":277,"userId":16,"queryType":0,"queryTypeName":"金融反欺诈","realName":"滕立波","idCard":"152123198904067218","telephone":"18306918831","queryResult":"{\"msg\":\"您的信用良好，请继续保持！\",\"score\":60,\"success\":true}","createdAt":"2019-08-07T12:25:36.000+0000","deleteFlg":0}]
     */

    private double pageNo;
    private double pageSize;
    private List<ResultListBean> resultList;

    public double getPageNo() {
        return pageNo;
    }

    public void setPageNo(double pageNo) {
        this.pageNo = pageNo;
    }

    public double getPageSize() {
        return pageSize;
    }

    public void setPageSize(double pageSize) {
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
         * id : 277.0
         * userId : 16.0
         * queryType : 0.0
         * queryTypeName : 金融反欺诈
         * realName : 滕立波
         * idCard : 152123198904067218
         * telephone : 18306918831
         * queryResult : {"msg":"您的信用良好，请继续保持！","score":60,"success":true}
         * createdAt : 2019-08-07T12:25:36.000+0000
         * deleteFlg : 0.0
         */

        private Long id;
        private Long userId;
        private Integer queryType;
        private String queryTypeName;
        private String realName;
        private String idCard;
        private String telephone;
        private String queryResult;
        private String createdAt;
        private Integer deleteFlg;

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

        public Integer getQueryType() {
            return queryType;
        }

        public void setQueryType(Integer queryType) {
            this.queryType = queryType;
        }

        public String getQueryTypeName() {
            return queryTypeName;
        }

        public void setQueryTypeName(String queryTypeName) {
            this.queryTypeName = queryTypeName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getQueryResult() {
            return queryResult;
        }

        public void setQueryResult(String queryResult) {
            this.queryResult = queryResult;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getDeleteFlg() {
            return deleteFlg;
        }

        public void setDeleteFlg(Integer deleteFlg) {
            this.deleteFlg = deleteFlg;
        }
    }
}
