package com.jie.exception.expectResult;

/**
 * ClassName: RequestParamException
 *
 * @Author: WangYiHai
 * @Date: 2020/7/10 11:36
 * @Description: TODO
 */
public class RequestParamException extends RuntimeException {

    private CodeEnum resultEnum;

    public RequestParamException(CodeEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    public CodeEnum getResultEnum() {
        return resultEnum;
    }

    @Override
    public String getMessage() {
        return resultEnum.getMsg();
    }

    public String getCode() {
        return resultEnum.getCode();
    }
}
