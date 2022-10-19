package com.xiaomin.blog.service;

import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.UserVo;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 20:17
 */
public interface SysUserService {
    UserVo findUserVoById(Long id);
    SysUser findUserById(Long id);

    SysUser findUser(String account, String password);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账户查照用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);
}
