package com.xiaomin.blog.controller;

import com.xiaomin.blog.service.LoginService;
import com.xiaomin.blog.service.SysUserService;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 14:25
 */
@RestController
@RequestMapping("login")
public class LoginController {

    //@Autowired
    //private SysUserService sysUserService;
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //登录 验证用户 访问用户表,但是
        return loginService.login(loginParam);
    }
}
