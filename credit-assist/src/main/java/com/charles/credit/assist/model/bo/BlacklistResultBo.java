package com.charles.credit.assist.model.bo;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 10:18
 */
public class BlacklistResultBo {

    /**
     * identityRelatedRiskBO : {"mobileRelationEmail6m":0,"idCardRelationMobile6m":0,"mobileRelationDevice6m":1,"idCardRelationEmail6m":0,"mobileRelationIdCard6m":1,"idCardRelationDevice6m":0}
     * personInfoBO : {"creditLevel":"优秀","address":"云南 - 西双版纳傣族自治州","reportDate":"2019-08-02","reportId":"156471219754618306918831","phone":"183****8831","idCard":"152***********7218","name":"滕立波","refuseRate":57,"age":30}
     * multiRiskBO : {"idCardRiskP2P3m":3,"mobileRiskP2P1m":1,"mobileRiskThirdPart6m":1,"idCardRiskConsumerFinance6m":2,"mobileRiskConsumerFinance1m":0,"mobileRiskConsumerFinance3m":1,"mobileRiskMicroFinance6m":2,"mobileRiskP2P3m":3,"mobileRiskMicroFinance1m":1,"idCardRiskThirdPart6m":1,"mobileRiskMicroFinance3m":1,"mobileRiskVerticalFinance1m":2,"idCardRiskMicroFinance6m":2,"mobileRiskVerticalFinance3m":2,"idCardRiskTotal3m":8,"idCardRiskTotal1m":5,"idCardRiskP2P1m":1,"idCardRiskVerticalFinance6m":2,"mobileRiskThirdPart1m":1,"mobileRiskThirdPart3m":1,"idCardRiskP2P6m":7,"mobileRiskConsumerFinance6m":2,"idCardRiskConsumerFinance3m":1,"mobileRiskP2P6m":7,"idCardRiskConsumerFinance1m":0,"mobileRiskVerticalFinance6m":2,"idCardRiskMicroFinance3m":1,"idCardRiskVerticalFinance3m":2,"idCardRiskThirdPart1m":1,"idCardRiskVerticalFinance1m":2,"idCardRiskTotal6m":14,"idCardRiskMicroFinance1m":1,"idCardRiskThirdPart3m":1}
     * contactHitBlackListBO : null
     * creditRiskBO : {"industryBlackList":false,"netLoanBlackList":false,"courtFaithlessBlackList":false,"econnoisseurRisk":false}
     */

    private IdentityRelatedRiskBOBean identityRelatedRiskBO;
    private PersonInfoBOBean personInfoBO;
    private MultiRiskBOBean multiRiskBO;
    private Object contactHitBlackListBO;
    private CreditRiskBOBean creditRiskBO;

    public IdentityRelatedRiskBOBean getIdentityRelatedRiskBO() {
        return identityRelatedRiskBO;
    }

    public void setIdentityRelatedRiskBO(IdentityRelatedRiskBOBean identityRelatedRiskBO) {
        this.identityRelatedRiskBO = identityRelatedRiskBO;
    }

    public PersonInfoBOBean getPersonInfoBO() {
        return personInfoBO;
    }

    public void setPersonInfoBO(PersonInfoBOBean personInfoBO) {
        this.personInfoBO = personInfoBO;
    }

    public MultiRiskBOBean getMultiRiskBO() {
        return multiRiskBO;
    }

    public void setMultiRiskBO(MultiRiskBOBean multiRiskBO) {
        this.multiRiskBO = multiRiskBO;
    }

    public Object getContactHitBlackListBO() {
        return contactHitBlackListBO;
    }

    public void setContactHitBlackListBO(Object contactHitBlackListBO) {
        this.contactHitBlackListBO = contactHitBlackListBO;
    }

    public CreditRiskBOBean getCreditRiskBO() {
        return creditRiskBO;
    }

    public void setCreditRiskBO(CreditRiskBOBean creditRiskBO) {
        this.creditRiskBO = creditRiskBO;
    }

    public static class IdentityRelatedRiskBOBean {
        /**
         * mobileRelationEmail6m : 0
         * idCardRelationMobile6m : 0
         * mobileRelationDevice6m : 1
         * idCardRelationEmail6m : 0
         * mobileRelationIdCard6m : 1
         * idCardRelationDevice6m : 0
         */

        private int mobileRelationEmail6m;
        private int idCardRelationMobile6m;
        private int mobileRelationDevice6m;
        private int idCardRelationEmail6m;
        private int mobileRelationIdCard6m;
        private int idCardRelationDevice6m;

        public int getMobileRelationEmail6m() {
            return mobileRelationEmail6m;
        }

        public void setMobileRelationEmail6m(int mobileRelationEmail6m) {
            this.mobileRelationEmail6m = mobileRelationEmail6m;
        }

        public int getIdCardRelationMobile6m() {
            return idCardRelationMobile6m;
        }

        public void setIdCardRelationMobile6m(int idCardRelationMobile6m) {
            this.idCardRelationMobile6m = idCardRelationMobile6m;
        }

        public int getMobileRelationDevice6m() {
            return mobileRelationDevice6m;
        }

        public void setMobileRelationDevice6m(int mobileRelationDevice6m) {
            this.mobileRelationDevice6m = mobileRelationDevice6m;
        }

        public int getIdCardRelationEmail6m() {
            return idCardRelationEmail6m;
        }

        public void setIdCardRelationEmail6m(int idCardRelationEmail6m) {
            this.idCardRelationEmail6m = idCardRelationEmail6m;
        }

        public int getMobileRelationIdCard6m() {
            return mobileRelationIdCard6m;
        }

        public void setMobileRelationIdCard6m(int mobileRelationIdCard6m) {
            this.mobileRelationIdCard6m = mobileRelationIdCard6m;
        }

        public int getIdCardRelationDevice6m() {
            return idCardRelationDevice6m;
        }

        public void setIdCardRelationDevice6m(int idCardRelationDevice6m) {
            this.idCardRelationDevice6m = idCardRelationDevice6m;
        }
    }

    public static class PersonInfoBOBean {
        /**
         * creditLevel : 优秀
         * address : 云南 - 西双版纳傣族自治州
         * reportDate : 2019-08-02
         * reportId : 156471219754618306918831
         * phone : 183****8831
         * idCard : 152***********7218
         * name : 滕立波
         * refuseRate : 57
         * age : 30
         */

        private String creditLevel;
        private String address;
        private String reportDate;
        private String reportId;
        private String phone;
        private String idCard;
        private String name;
        private int refuseRate;
        private int age;

        public String getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(String creditLevel) {
            this.creditLevel = creditLevel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReportDate() {
            return reportDate;
        }

        public void setReportDate(String reportDate) {
            this.reportDate = reportDate;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRefuseRate() {
            return refuseRate;
        }

        public void setRefuseRate(int refuseRate) {
            this.refuseRate = refuseRate;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class MultiRiskBOBean {
        /**
         * idCardRiskP2P3m : 3
         * mobileRiskP2P1m : 1
         * mobileRiskThirdPart6m : 1
         * idCardRiskConsumerFinance6m : 2
         * mobileRiskConsumerFinance1m : 0
         * mobileRiskConsumerFinance3m : 1
         * mobileRiskMicroFinance6m : 2
         * mobileRiskP2P3m : 3
         * mobileRiskMicroFinance1m : 1
         * idCardRiskThirdPart6m : 1
         * mobileRiskMicroFinance3m : 1
         * mobileRiskVerticalFinance1m : 2
         * idCardRiskMicroFinance6m : 2
         * mobileRiskVerticalFinance3m : 2
         * idCardRiskTotal3m : 8
         * idCardRiskTotal1m : 5
         * idCardRiskP2P1m : 1
         * idCardRiskVerticalFinance6m : 2
         * mobileRiskThirdPart1m : 1
         * mobileRiskThirdPart3m : 1
         * idCardRiskP2P6m : 7
         * mobileRiskConsumerFinance6m : 2
         * idCardRiskConsumerFinance3m : 1
         * mobileRiskP2P6m : 7
         * idCardRiskConsumerFinance1m : 0
         * mobileRiskVerticalFinance6m : 2
         * idCardRiskMicroFinance3m : 1
         * idCardRiskVerticalFinance3m : 2
         * idCardRiskThirdPart1m : 1
         * idCardRiskVerticalFinance1m : 2
         * idCardRiskTotal6m : 14
         * idCardRiskMicroFinance1m : 1
         * idCardRiskThirdPart3m : 1
         */

        private int idCardRiskP2P3m;
        private int mobileRiskP2P1m;
        private int mobileRiskThirdPart6m;
        private int idCardRiskConsumerFinance6m;
        private int mobileRiskConsumerFinance1m;
        private int mobileRiskConsumerFinance3m;
        private int mobileRiskMicroFinance6m;
        private int mobileRiskP2P3m;
        private int mobileRiskMicroFinance1m;
        private int idCardRiskThirdPart6m;
        private int mobileRiskMicroFinance3m;
        private int mobileRiskVerticalFinance1m;
        private int idCardRiskMicroFinance6m;
        private int mobileRiskVerticalFinance3m;
        private int idCardRiskTotal3m;
        private int idCardRiskTotal1m;
        private int idCardRiskP2P1m;
        private int idCardRiskVerticalFinance6m;
        private int mobileRiskThirdPart1m;
        private int mobileRiskThirdPart3m;
        private int idCardRiskP2P6m;
        private int mobileRiskConsumerFinance6m;
        private int idCardRiskConsumerFinance3m;
        private int mobileRiskP2P6m;
        private int idCardRiskConsumerFinance1m;
        private int mobileRiskVerticalFinance6m;
        private int idCardRiskMicroFinance3m;
        private int idCardRiskVerticalFinance3m;
        private int idCardRiskThirdPart1m;
        private int idCardRiskVerticalFinance1m;
        private int idCardRiskTotal6m;
        private int idCardRiskMicroFinance1m;
        private int idCardRiskThirdPart3m;

        public int getIdCardRiskP2P3m() {
            return idCardRiskP2P3m;
        }

        public void setIdCardRiskP2P3m(int idCardRiskP2P3m) {
            this.idCardRiskP2P3m = idCardRiskP2P3m;
        }

        public int getMobileRiskP2P1m() {
            return mobileRiskP2P1m;
        }

        public void setMobileRiskP2P1m(int mobileRiskP2P1m) {
            this.mobileRiskP2P1m = mobileRiskP2P1m;
        }

        public int getMobileRiskThirdPart6m() {
            return mobileRiskThirdPart6m;
        }

        public void setMobileRiskThirdPart6m(int mobileRiskThirdPart6m) {
            this.mobileRiskThirdPart6m = mobileRiskThirdPart6m;
        }

        public int getIdCardRiskConsumerFinance6m() {
            return idCardRiskConsumerFinance6m;
        }

        public void setIdCardRiskConsumerFinance6m(int idCardRiskConsumerFinance6m) {
            this.idCardRiskConsumerFinance6m = idCardRiskConsumerFinance6m;
        }

        public int getMobileRiskConsumerFinance1m() {
            return mobileRiskConsumerFinance1m;
        }

        public void setMobileRiskConsumerFinance1m(int mobileRiskConsumerFinance1m) {
            this.mobileRiskConsumerFinance1m = mobileRiskConsumerFinance1m;
        }

        public int getMobileRiskConsumerFinance3m() {
            return mobileRiskConsumerFinance3m;
        }

        public void setMobileRiskConsumerFinance3m(int mobileRiskConsumerFinance3m) {
            this.mobileRiskConsumerFinance3m = mobileRiskConsumerFinance3m;
        }

        public int getMobileRiskMicroFinance6m() {
            return mobileRiskMicroFinance6m;
        }

        public void setMobileRiskMicroFinance6m(int mobileRiskMicroFinance6m) {
            this.mobileRiskMicroFinance6m = mobileRiskMicroFinance6m;
        }

        public int getMobileRiskP2P3m() {
            return mobileRiskP2P3m;
        }

        public void setMobileRiskP2P3m(int mobileRiskP2P3m) {
            this.mobileRiskP2P3m = mobileRiskP2P3m;
        }

        public int getMobileRiskMicroFinance1m() {
            return mobileRiskMicroFinance1m;
        }

        public void setMobileRiskMicroFinance1m(int mobileRiskMicroFinance1m) {
            this.mobileRiskMicroFinance1m = mobileRiskMicroFinance1m;
        }

        public int getIdCardRiskThirdPart6m() {
            return idCardRiskThirdPart6m;
        }

        public void setIdCardRiskThirdPart6m(int idCardRiskThirdPart6m) {
            this.idCardRiskThirdPart6m = idCardRiskThirdPart6m;
        }

        public int getMobileRiskMicroFinance3m() {
            return mobileRiskMicroFinance3m;
        }

        public void setMobileRiskMicroFinance3m(int mobileRiskMicroFinance3m) {
            this.mobileRiskMicroFinance3m = mobileRiskMicroFinance3m;
        }

        public int getMobileRiskVerticalFinance1m() {
            return mobileRiskVerticalFinance1m;
        }

        public void setMobileRiskVerticalFinance1m(int mobileRiskVerticalFinance1m) {
            this.mobileRiskVerticalFinance1m = mobileRiskVerticalFinance1m;
        }

        public int getIdCardRiskMicroFinance6m() {
            return idCardRiskMicroFinance6m;
        }

        public void setIdCardRiskMicroFinance6m(int idCardRiskMicroFinance6m) {
            this.idCardRiskMicroFinance6m = idCardRiskMicroFinance6m;
        }

        public int getMobileRiskVerticalFinance3m() {
            return mobileRiskVerticalFinance3m;
        }

        public void setMobileRiskVerticalFinance3m(int mobileRiskVerticalFinance3m) {
            this.mobileRiskVerticalFinance3m = mobileRiskVerticalFinance3m;
        }

        public int getIdCardRiskTotal3m() {
            return idCardRiskTotal3m;
        }

        public void setIdCardRiskTotal3m(int idCardRiskTotal3m) {
            this.idCardRiskTotal3m = idCardRiskTotal3m;
        }

        public int getIdCardRiskTotal1m() {
            return idCardRiskTotal1m;
        }

        public void setIdCardRiskTotal1m(int idCardRiskTotal1m) {
            this.idCardRiskTotal1m = idCardRiskTotal1m;
        }

        public int getIdCardRiskP2P1m() {
            return idCardRiskP2P1m;
        }

        public void setIdCardRiskP2P1m(int idCardRiskP2P1m) {
            this.idCardRiskP2P1m = idCardRiskP2P1m;
        }

        public int getIdCardRiskVerticalFinance6m() {
            return idCardRiskVerticalFinance6m;
        }

        public void setIdCardRiskVerticalFinance6m(int idCardRiskVerticalFinance6m) {
            this.idCardRiskVerticalFinance6m = idCardRiskVerticalFinance6m;
        }

        public int getMobileRiskThirdPart1m() {
            return mobileRiskThirdPart1m;
        }

        public void setMobileRiskThirdPart1m(int mobileRiskThirdPart1m) {
            this.mobileRiskThirdPart1m = mobileRiskThirdPart1m;
        }

        public int getMobileRiskThirdPart3m() {
            return mobileRiskThirdPart3m;
        }

        public void setMobileRiskThirdPart3m(int mobileRiskThirdPart3m) {
            this.mobileRiskThirdPart3m = mobileRiskThirdPart3m;
        }

        public int getIdCardRiskP2P6m() {
            return idCardRiskP2P6m;
        }

        public void setIdCardRiskP2P6m(int idCardRiskP2P6m) {
            this.idCardRiskP2P6m = idCardRiskP2P6m;
        }

        public int getMobileRiskConsumerFinance6m() {
            return mobileRiskConsumerFinance6m;
        }

        public void setMobileRiskConsumerFinance6m(int mobileRiskConsumerFinance6m) {
            this.mobileRiskConsumerFinance6m = mobileRiskConsumerFinance6m;
        }

        public int getIdCardRiskConsumerFinance3m() {
            return idCardRiskConsumerFinance3m;
        }

        public void setIdCardRiskConsumerFinance3m(int idCardRiskConsumerFinance3m) {
            this.idCardRiskConsumerFinance3m = idCardRiskConsumerFinance3m;
        }

        public int getMobileRiskP2P6m() {
            return mobileRiskP2P6m;
        }

        public void setMobileRiskP2P6m(int mobileRiskP2P6m) {
            this.mobileRiskP2P6m = mobileRiskP2P6m;
        }

        public int getIdCardRiskConsumerFinance1m() {
            return idCardRiskConsumerFinance1m;
        }

        public void setIdCardRiskConsumerFinance1m(int idCardRiskConsumerFinance1m) {
            this.idCardRiskConsumerFinance1m = idCardRiskConsumerFinance1m;
        }

        public int getMobileRiskVerticalFinance6m() {
            return mobileRiskVerticalFinance6m;
        }

        public void setMobileRiskVerticalFinance6m(int mobileRiskVerticalFinance6m) {
            this.mobileRiskVerticalFinance6m = mobileRiskVerticalFinance6m;
        }

        public int getIdCardRiskMicroFinance3m() {
            return idCardRiskMicroFinance3m;
        }

        public void setIdCardRiskMicroFinance3m(int idCardRiskMicroFinance3m) {
            this.idCardRiskMicroFinance3m = idCardRiskMicroFinance3m;
        }

        public int getIdCardRiskVerticalFinance3m() {
            return idCardRiskVerticalFinance3m;
        }

        public void setIdCardRiskVerticalFinance3m(int idCardRiskVerticalFinance3m) {
            this.idCardRiskVerticalFinance3m = idCardRiskVerticalFinance3m;
        }

        public int getIdCardRiskThirdPart1m() {
            return idCardRiskThirdPart1m;
        }

        public void setIdCardRiskThirdPart1m(int idCardRiskThirdPart1m) {
            this.idCardRiskThirdPart1m = idCardRiskThirdPart1m;
        }

        public int getIdCardRiskVerticalFinance1m() {
            return idCardRiskVerticalFinance1m;
        }

        public void setIdCardRiskVerticalFinance1m(int idCardRiskVerticalFinance1m) {
            this.idCardRiskVerticalFinance1m = idCardRiskVerticalFinance1m;
        }

        public int getIdCardRiskTotal6m() {
            return idCardRiskTotal6m;
        }

        public void setIdCardRiskTotal6m(int idCardRiskTotal6m) {
            this.idCardRiskTotal6m = idCardRiskTotal6m;
        }

        public int getIdCardRiskMicroFinance1m() {
            return idCardRiskMicroFinance1m;
        }

        public void setIdCardRiskMicroFinance1m(int idCardRiskMicroFinance1m) {
            this.idCardRiskMicroFinance1m = idCardRiskMicroFinance1m;
        }

        public int getIdCardRiskThirdPart3m() {
            return idCardRiskThirdPart3m;
        }

        public void setIdCardRiskThirdPart3m(int idCardRiskThirdPart3m) {
            this.idCardRiskThirdPart3m = idCardRiskThirdPart3m;
        }
    }

    public static class CreditRiskBOBean {
        /**
         * industryBlackList : false
         * netLoanBlackList : false
         * courtFaithlessBlackList : false
         * econnoisseurRisk : false
         */

        private boolean industryBlackList;
        private boolean netLoanBlackList;
        private boolean courtFaithlessBlackList;
        private boolean econnoisseurRisk;

        public boolean isIndustryBlackList() {
            return industryBlackList;
        }

        public void setIndustryBlackList(boolean industryBlackList) {
            this.industryBlackList = industryBlackList;
        }

        public boolean isNetLoanBlackList() {
            return netLoanBlackList;
        }

        public void setNetLoanBlackList(boolean netLoanBlackList) {
            this.netLoanBlackList = netLoanBlackList;
        }

        public boolean isCourtFaithlessBlackList() {
            return courtFaithlessBlackList;
        }

        public void setCourtFaithlessBlackList(boolean courtFaithlessBlackList) {
            this.courtFaithlessBlackList = courtFaithlessBlackList;
        }

        public boolean isEconnoisseurRisk() {
            return econnoisseurRisk;
        }

        public void setEconnoisseurRisk(boolean econnoisseurRisk) {
            this.econnoisseurRisk = econnoisseurRisk;
        }
    }
}
