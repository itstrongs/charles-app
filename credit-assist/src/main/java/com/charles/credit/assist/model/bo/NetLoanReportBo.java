package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 21:40
 */
public class NetLoanReportBo {
    /**
     * reportId : 156393842668518306918831
     * loanInfoList : [{"platformName":"上海你我贷互联网金融信息**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"拍拍信数据**（上海）有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"上海仟才金融信息**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"凡普金科**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"新疆玖富万卡信息**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"霍尔果斯智融未来信息**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"},{"platformName":"上海科赋网络**有限公司","platformIcon":"http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png","status":"REGISTER"}]
     * phone : 183****8831
     * idCard : 152***********7218
     * sex : 1
     * name : 滕立波
     * loanPlatCount : 7
     * age : 30
     */

    private String reportId;
    private String phone;
    private String idCard;
    private int sex;
    private String name;
    private int loanPlatCount;
    private int age;
    private List<LoanInfoListBean> loanInfoList;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoanPlatCount() {
        return loanPlatCount;
    }

    public void setLoanPlatCount(int loanPlatCount) {
        this.loanPlatCount = loanPlatCount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<LoanInfoListBean> getLoanInfoList() {
        return loanInfoList;
    }

    public void setLoanInfoList(List<LoanInfoListBean> loanInfoList) {
        this.loanInfoList = loanInfoList;
    }

    public static class LoanInfoListBean {
        /**
         * platformName : 上海你我贷互联网金融信息**有限公司
         * platformIcon : http://xinniu.oss-cn-hangzhou.aliyuncs.com/bscloud/papilio/2018/11/22/59a2f6b9c2c74d1db5388d0a996ab3c7.png
         * status : REGISTER
         */

        private String platformName;
        private String platformIcon;
        private String status;

        public String getPlatformName() {
            return platformName;
        }

        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }

        public String getPlatformIcon() {
            return platformIcon;
        }

        public void setPlatformIcon(String platformIcon) {
            this.platformIcon = platformIcon;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
