package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月06日 21:00
 */
public class BlacklistCommonResultBo {
    /**
     * idCardBrokenPromise : 未命中
     * sixMonthP2pCount : 7
     * hitCount : 1
     * idCard : 1521**********7218
     * refuseRate : 55
     * oneMonthBankCount : 0
     * riskControl : [{"desc":"3个月内不要申请超过20次贷款，控制申请频率"}]
     * creditManagerId : 2
     * creditLevel : 优秀
     * applyNotSelf : 未命中
     * limit : 8000
     * threeMonthOtherCount : 4
     * oneMonthSmallCount : 1
     * limitDesc : 你的信用状况一般，建议选择高通过率贷款
     * overdueDesc : 未命中
     * threeMonthTotalCount : 8
     * threeMonthSmallCount : 1
     * address : 云南 - 西双版纳傣族自治州
     * reportId : 156516869811918306918831
     * sixMonthTotalCount : 13
     * sixMonthOtherCount : 4
     * sex : 1
     * oneMonthTotalCount : 5
     * threeMonthP2pCount : 3
     * oneMonthOtherCount : 3
     * sixMonthSmallCount : 2
     * refuseLevel : 较高
     * oneMonthP2pCount : 1
     * phoneRisk : 未命中
     * phone : 183****8831
     * name : 滕立波
     * sixMonthBankCount : 0
     * applyDebtRisk : 命中
     * threeMonthBankCount : 0
     * age : 30
     */

    private String idCardBrokenPromise;
    private int sixMonthP2pCount;
    private int hitCount;
    private String idCard;
    private int refuseRate;
    private int oneMonthBankCount;
    private int creditManagerId;
    private String creditLevel;
    private String applyNotSelf;
    private int limit;
    private int threeMonthOtherCount;
    private int oneMonthSmallCount;
    private String limitDesc;
    private String overdueDesc;
    private int threeMonthTotalCount;
    private int threeMonthSmallCount;
    private String address;
    private String reportId;
    private int sixMonthTotalCount;
    private int sixMonthOtherCount;
    private int sex;
    private int oneMonthTotalCount;
    private int threeMonthP2pCount;
    private int oneMonthOtherCount;
    private int sixMonthSmallCount;
    private String refuseLevel;
    private int oneMonthP2pCount;
    private String phoneRisk;
    private String phone;
    private String name;
    private int sixMonthBankCount;
    private String applyDebtRisk;
    private int threeMonthBankCount;
    private int age;
    private List<RiskControlBean> riskControl;

    public String getIdCardBrokenPromise() {
        return idCardBrokenPromise;
    }

    public void setIdCardBrokenPromise(String idCardBrokenPromise) {
        this.idCardBrokenPromise = idCardBrokenPromise;
    }

    public int getSixMonthP2pCount() {
        return sixMonthP2pCount;
    }

    public void setSixMonthP2pCount(int sixMonthP2pCount) {
        this.sixMonthP2pCount = sixMonthP2pCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getRefuseRate() {
        return refuseRate;
    }

    public void setRefuseRate(int refuseRate) {
        this.refuseRate = refuseRate;
    }

    public int getOneMonthBankCount() {
        return oneMonthBankCount;
    }

    public void setOneMonthBankCount(int oneMonthBankCount) {
        this.oneMonthBankCount = oneMonthBankCount;
    }

    public int getCreditManagerId() {
        return creditManagerId;
    }

    public void setCreditManagerId(int creditManagerId) {
        this.creditManagerId = creditManagerId;
    }

    public String getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(String creditLevel) {
        this.creditLevel = creditLevel;
    }

    public String getApplyNotSelf() {
        return applyNotSelf;
    }

    public void setApplyNotSelf(String applyNotSelf) {
        this.applyNotSelf = applyNotSelf;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getThreeMonthOtherCount() {
        return threeMonthOtherCount;
    }

    public void setThreeMonthOtherCount(int threeMonthOtherCount) {
        this.threeMonthOtherCount = threeMonthOtherCount;
    }

    public int getOneMonthSmallCount() {
        return oneMonthSmallCount;
    }

    public void setOneMonthSmallCount(int oneMonthSmallCount) {
        this.oneMonthSmallCount = oneMonthSmallCount;
    }

    public String getLimitDesc() {
        return limitDesc;
    }

    public void setLimitDesc(String limitDesc) {
        this.limitDesc = limitDesc;
    }

    public String getOverdueDesc() {
        return overdueDesc;
    }

    public void setOverdueDesc(String overdueDesc) {
        this.overdueDesc = overdueDesc;
    }

    public int getThreeMonthTotalCount() {
        return threeMonthTotalCount;
    }

    public void setThreeMonthTotalCount(int threeMonthTotalCount) {
        this.threeMonthTotalCount = threeMonthTotalCount;
    }

    public int getThreeMonthSmallCount() {
        return threeMonthSmallCount;
    }

    public void setThreeMonthSmallCount(int threeMonthSmallCount) {
        this.threeMonthSmallCount = threeMonthSmallCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public int getSixMonthTotalCount() {
        return sixMonthTotalCount;
    }

    public void setSixMonthTotalCount(int sixMonthTotalCount) {
        this.sixMonthTotalCount = sixMonthTotalCount;
    }

    public int getSixMonthOtherCount() {
        return sixMonthOtherCount;
    }

    public void setSixMonthOtherCount(int sixMonthOtherCount) {
        this.sixMonthOtherCount = sixMonthOtherCount;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getOneMonthTotalCount() {
        return oneMonthTotalCount;
    }

    public void setOneMonthTotalCount(int oneMonthTotalCount) {
        this.oneMonthTotalCount = oneMonthTotalCount;
    }

    public int getThreeMonthP2pCount() {
        return threeMonthP2pCount;
    }

    public void setThreeMonthP2pCount(int threeMonthP2pCount) {
        this.threeMonthP2pCount = threeMonthP2pCount;
    }

    public int getOneMonthOtherCount() {
        return oneMonthOtherCount;
    }

    public void setOneMonthOtherCount(int oneMonthOtherCount) {
        this.oneMonthOtherCount = oneMonthOtherCount;
    }

    public int getSixMonthSmallCount() {
        return sixMonthSmallCount;
    }

    public void setSixMonthSmallCount(int sixMonthSmallCount) {
        this.sixMonthSmallCount = sixMonthSmallCount;
    }

    public String getRefuseLevel() {
        return refuseLevel;
    }

    public void setRefuseLevel(String refuseLevel) {
        this.refuseLevel = refuseLevel;
    }

    public int getOneMonthP2pCount() {
        return oneMonthP2pCount;
    }

    public void setOneMonthP2pCount(int oneMonthP2pCount) {
        this.oneMonthP2pCount = oneMonthP2pCount;
    }

    public String getPhoneRisk() {
        return phoneRisk;
    }

    public void setPhoneRisk(String phoneRisk) {
        this.phoneRisk = phoneRisk;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSixMonthBankCount() {
        return sixMonthBankCount;
    }

    public void setSixMonthBankCount(int sixMonthBankCount) {
        this.sixMonthBankCount = sixMonthBankCount;
    }

    public String getApplyDebtRisk() {
        return applyDebtRisk;
    }

    public void setApplyDebtRisk(String applyDebtRisk) {
        this.applyDebtRisk = applyDebtRisk;
    }

    public int getThreeMonthBankCount() {
        return threeMonthBankCount;
    }

    public void setThreeMonthBankCount(int threeMonthBankCount) {
        this.threeMonthBankCount = threeMonthBankCount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<RiskControlBean> getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(List<RiskControlBean> riskControl) {
        this.riskControl = riskControl;
    }

    public static class RiskControlBean {
        /**
         * desc : 3个月内不要申请超过20次贷款，控制申请频率
         */

        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
