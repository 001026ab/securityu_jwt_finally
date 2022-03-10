package com.jie.exception.expectResult;

/**
 * @author ReStartLin
 * @data 2019/3/11 11:09
 * @classDesc: 功能描述: 返回枚举值
 * 　　100-199 用于指定客户端应相应的某些动作。
 *
 * 　　200-299 用于表示请求成功。
 *
 * 　　300-399 用于已经移动的文件并且常被包含在定位头信息中指定新的地址信息。
 *
 * 　　400-499 用于指出客户端的错误。
 *
 * 　　500-599 用于支持服务器错误。
 */


public enum ResultEnum implements CodeEnum {
    /**
     *
     */
    USER_NAME_ERROR("03001","登录用户不存在"),

    ;

    /**
     * 状态码
     */
    private String code;
    /**
     * 信息
     */
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }


    @Override
    public String getMsg() {
        return msg;
    }



}
