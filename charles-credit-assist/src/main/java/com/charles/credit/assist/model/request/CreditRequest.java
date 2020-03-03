package com.charles.credit.assist.model.request;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月28日 12:05
 */
public class CreditRequest {

    private Integer queryType;

    private String realName;

    private String idCard;

    private String telephone;

    /**
     * 紧急联系人
     */
    private List<UrgencyContactRequest> urgencyContacts;

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
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

    public List<UrgencyContactRequest> getUrgencyContacts() {
        return urgencyContacts;
    }

    public void setUrgencyContacts(List<UrgencyContactRequest> urgencyContacts) {
        this.urgencyContacts = urgencyContacts;
    }
}
