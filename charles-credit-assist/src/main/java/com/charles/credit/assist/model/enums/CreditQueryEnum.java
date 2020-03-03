package com.charles.credit.assist.model.enums;

/**
 * 说明
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月30日 15:40
 */
public enum CreditQueryEnum {

    ANTI_FRAUD(0, "反欺诈"),
    BLACK_LIST(1, "黑名单"),
    LOANS_RECORD(2, "网贷申请记录"),
    SET_MEAL(3, "三合一套餐"),
    EMERGENCY_CONTACT(4, "紧急联系人检测");

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    CreditQueryEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CreditQueryEnum getEnumByCode(Integer code) {
        for (CreditQueryEnum enums : CreditQueryEnum.values()) {
            if (code.equals(enums.getCode())) {
                return enums;
            }
        }
        return null;
    }
}
