package com.jie.filter;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jie.exception.TokenIsExpiredException;
import com.jie.service.impl.UserDetailsServiceImpl;
import com.jie.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 授权拦截器
 * （登录token过滤器验证完成后进入这个过滤器）
 *
 *
 */

/**
 * 验证成功当然就是进行鉴权了
 * 登录成功之后走此类进行鉴权操作
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

   /* @Autowired
    private UserDetailsService userDetailsService;
*/
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
       //输入的token名称为Authorization
        String tokenHeader = request.getHeader("Authorization");
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || tokenHeader == "") {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        try {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        } catch (TokenIsExpiredException e) {
            //返回json形式的错误信息
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String reason = "统一处理，原因：" + e.getMessage();
            response.getWriter().write(new ObjectMapper().writeValueAsString(reason));
            response.getWriter().flush();
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) throws TokenIsExpiredException {
        String token = tokenHeader;
        System.out.println("获得的token: "+token);
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new TokenIsExpiredException("token超时了");
        } else {
            //糊涂工具手动注入bean
            UserDetailsService userDetailsService = SpringUtil.getBean(UserDetailsService.class);
            log.info("注入Bean：{}",userDetailsService);
            String username = JwtTokenUtils.getUsername(token);
            //不从token中获取bean，而是从新查询权限List
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            //从token中获取用户全权限，每个人只有一个权限时适用
          //  String role = JwtTokenUtils.getUserRole(token);
            if (username != null) {
             //   log.info("这里从token中获取用户信息并新建一个token：{}",new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities()));
                return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                //每个人只有一个权限时
              /*  return new UsernamePasswordAuthenticationToken(username, null,
                  Collections.singleton(new SimpleGrantedAuthority(role)));*/

            }
        }
        return null;
    }

}
