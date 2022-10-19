package com.xiaomin.blog.admin.service;

import com.xiaomin.blog.admin.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 21:30
 */
@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //当你登录的时候,会把username传递到这里
        //通过username查询用户表admin表,如果admin存在 将密码告诉spring security
        //如果不存在 返回null 代表认证失败
        Admin admin=adminService.findAdminByUsername(username);
        if (admin == null) {
            //登陆失败
            return null;
        }
        UserDetails userDetails=new User(username,admin.getPassword(),new ArrayList<>());
        return userDetails;
    }
}
