package com.itunion.weather.common.enums;

public enum BaseCode {
    /** 成功状态码 **/
    SUCCESS("200", "请求成功"),
    /** 参数错误：1001-1999 **/
    PARAM_IS_INVALID("1001","参数无效"),
    PARAM_IS_BLANK("1002","参数为空"),
    PARAM_TYPE_BIND_ERROR("1003","参数类型错误"),
    PARAM_NOT_COMPLETE("1004","参数缺失"),
    PARAM_BLACKID_IS_BLANK("1005","错误的请求认证信息"),
    /** 用户错误：2001-2999 **/
    USER_NOT_LOGGED_IN("2001","用户未登陆"),
    USER_LOGIN_ERROR("2002","账号不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN("2003","账号已被禁用"),
    USER_NOT_EXIST("2004","用户不存在"),
    USER_HAS_EXISTED("2005","用户已存在"),
    /** 接口错误：3001-3999 **/
    FAIL_BLACK("4000", "请求异常！"),
    FAIL("4001", "请求的资源不存在"),
    USER_IS_BLACK("4002", "用户已被加入黑名单！"),
    unknown("5001", "内部服务器错误"),
    ;
    private String code;
    private String desc;
    BaseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public static BaseCode fromCode(String code) {
        for (BaseCode ec : BaseCode.values()) {
            if (ec.getCode().equals(code)) {
                return ec;
            }
        }
        return unknown;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据code获取desc
     * @param code
     * @return
     */
    public static String getDescByCode(String code){
        BaseCode[] values= BaseCode.values();
        for (BaseCode value: values) {
            if(value.getCode().equals(code)){
                return value.getDesc();
            }
        }
        return String.valueOf(code);
    }

    /**
     * 根据des获取code
     * @param desc
     * @return
     */
    public static String getCodeByDesc(String desc){
        BaseCode[] values= BaseCode.values();
        for (BaseCode value: values) {
            if (value.getDesc().equals(desc)){
                return value.getCode();
            }
        }
        return String.valueOf(desc);
    }

}
