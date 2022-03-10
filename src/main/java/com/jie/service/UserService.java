package com.jie.service;

import com.jie.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jie
 * @since 2021-10-17
 */
public interface UserService extends IService<User> {
    User selectUserByUserName(String username);
}
