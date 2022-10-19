package com.xiaomin.blog.service;

import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 14:28
 */
public interface LoginService {
    /**
     * 登录功能
     * @param loginParam
     * @return
     */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 注册
     * @param loginParam
     * @return
     */
    Result register(LoginParam loginParam);
}
