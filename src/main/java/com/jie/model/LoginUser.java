package com.jie.model;

import lombok.Data;

/**
 * 获取登录中输入的账号密码，在过滤器中使用
 * @author ufi
 */
@Data
public class LoginUser {
    private String username;
    private String password;
}
