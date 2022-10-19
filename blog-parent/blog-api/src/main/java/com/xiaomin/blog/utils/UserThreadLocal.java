package com.xiaomin.blog.utils;

import com.xiaomin.blog.dao.pojo.SysUser;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 22:18
 */
public class UserThreadLocal {

    private UserThreadLocal(){}
    //线程变量隔离
    private static final ThreadLocal<SysUser> LOCAL=new ThreadLocal<>();
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
