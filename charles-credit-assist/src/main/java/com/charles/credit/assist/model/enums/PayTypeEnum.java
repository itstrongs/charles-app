package com.charles.credit.assist.model.enums;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/6/14
 */
public enum PayTypeEnum {

    ALIPAY(0, "alipay", "支付宝"),

    WECHAT(1, "wechat", "微信");

    private Integer code;

    private String value;

    private String name;

    PayTypeEnum(Integer code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
