package com.charles.credit.assist.model.bo;

import java.util.List;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月03日 00:21
 */
public class ContactResultBo {
    /**
     * matchCnt : 2
     * freeUpdates : false
     * guidePhoneRisk : true
     * notMatchCnt : 0
     * contacts : [{"phone":"151****7796","name":"张三","match":true,"hitRisk":false},{"phone":"151****7793","name":"李四","match":true,"hitRisk":false}]
     */

    private int matchCnt;
    private boolean freeUpdates;
    private boolean guidePhoneRisk;
    private int notMatchCnt;
    private List<ContactsBean> contacts;

    public int getMatchCnt() {
        return matchCnt;
    }

    public void setMatchCnt(int matchCnt) {
        this.matchCnt = matchCnt;
    }

    public boolean isFreeUpdates() {
        return freeUpdates;
    }

    public void setFreeUpdates(boolean freeUpdates) {
        this.freeUpdates = freeUpdates;
    }

    public boolean isGuidePhoneRisk() {
        return guidePhoneRisk;
    }

    public void setGuidePhoneRisk(boolean guidePhoneRisk) {
        this.guidePhoneRisk = guidePhoneRisk;
    }

    public int getNotMatchCnt() {
        return notMatchCnt;
    }

    public void setNotMatchCnt(int notMatchCnt) {
        this.notMatchCnt = notMatchCnt;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    public static class ContactsBean {
        /**
         * phone : 151****7796
         * name : 张三
         * match : true
         * hitRisk : false
         */

        private String phone;
        private String name;
        private boolean match;
        private boolean hitRisk;

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

        public boolean isMatch() {
            return match;
        }

        public void setMatch(boolean match) {
            this.match = match;
        }

        public boolean isHitRisk() {
            return hitRisk;
        }

        public void setHitRisk(boolean hitRisk) {
            this.hitRisk = hitRisk;
        }
    }
}
