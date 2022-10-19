package com.xiaomin.blog.controller;

import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.utils.UserThreadLocal;
import com.xiaomin.blog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 22:08
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping
    public Result test(){
        SysUser sysUser= UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
