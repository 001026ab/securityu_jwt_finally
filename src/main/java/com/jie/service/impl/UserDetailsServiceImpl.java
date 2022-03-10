package com.jie.service.impl;

import com.jie.entity.JwtUser;
import com.jie.entity.User;
import com.jie.exception.expectResult.RequestParamException;
import com.jie.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.jie.exception.expectResult.ResultEnum.USER_NAME_ERROR;

/**
 * 获取用户权限，角色
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    /**
     * 获取用户权限，获取用户的具体信息
     * 登录时验证账号密码，首先进入这里获取用户的具体信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);
        System.out.println(user);
        if (user == null) {
            throw new RequestParamException(USER_NAME_ERROR);
            //  throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        List<SimpleGrantedAuthority> authorities = authorities(user.getRole());

       /*  JwtUser jwtUser = new JwtUser(user);
        return jwtUser;*/
        return new JwtUser(user.getId(), user.getUsername(),
                user.getPassword(), authorities);
        // return new JwtUser();
    }

    /**
     * 一个用户有多个权限-获取用户权限列表,根据自己的数据库逻辑划分
     *
     * @param permissions
     * @return
     */
    private List<SimpleGrantedAuthority> authorities(String permissions) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        String[] authorities = permissions.split(",");
        for (String authority : authorities) {
            roles.add(new SimpleGrantedAuthority(authority));
        }
        System.out.println("用户角色" + roles);
        return roles;
    }
}
