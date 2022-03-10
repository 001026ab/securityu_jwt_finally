package com.jie.exception.expectResult;

/**
 * ClassName: CodeEnum
 *
 * @Author: WangYiHai
 * @Date: 2020/4/27 10:36
 * @Description: TODO
 */
public interface CodeEnum {
    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMsg();


}