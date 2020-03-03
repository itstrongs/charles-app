package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月01日 11:18
 */
public class CouponListBo {

    /**
     * pageNo : 1
     * pageSize : 10
     * resultList : [{"id":24,"userId":16,"money":5,"sourceBy":13,"name":"通用优惠券","invalidTime":"2019-07-25T08:42:55.000+0000","type":0,"createdAt":"2019-07-25T08:42:55.000+0000","updateAt":null,"deleteAt":null,"deleteFlg":0},{"id":23,"userId":16,"money":5,"sourceBy":14,"name":"通用优惠券","invalidTime":"2019-07-25T08:42:34.000+0000","type":0,"createdAt":"2019-07-25T08:42:34.000+0000","updateAt":null,"deleteAt":null,"deleteFlg":0}]
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
         * id : 24
         * userId : 16
         * money : 5
         * sourceBy : 13
         * name : 通用优惠券
         * invalidTime : 2019-07-25T08:42:55.000+0000
         * type : 0
         * createdAt : 2019-07-25T08:42:55.000+0000
         * updateAt : null
         * deleteAt : null
         * deleteFlg : 0
         */

        private int id;
        private int userId;
        private int money;
        private int sourceBy;
        private String name;
        private String invalidTime;
        private int type;
        private String createdAt;
        private Object updateAt;
        private Object deleteAt;
        private int deleteFlg;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getSourceBy() {
            return sourceBy;
        }

        public void setSourceBy(int sourceBy) {
            this.sourceBy = sourceBy;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInvalidTime() {
            return invalidTime;
        }

        public void setInvalidTime(String invalidTime) {
            this.invalidTime = invalidTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(Object updateAt) {
            this.updateAt = updateAt;
        }

        public Object getDeleteAt() {
            return deleteAt;
        }

        public void setDeleteAt(Object deleteAt) {
            this.deleteAt = deleteAt;
        }

        public int getDeleteFlg() {
            return deleteFlg;
        }

        public void setDeleteFlg(int deleteFlg) {
            this.deleteFlg = deleteFlg;
        }
    }
}
