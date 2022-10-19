package com.xiaomin.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomin.blog.admin.mapper.AdminMapper;
import com.xiaomin.blog.admin.pojo.Admin;
import com.xiaomin.blog.admin.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 21:33
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Admin findAdminByUsername(String username){
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.last("limit 1");
        Admin admin = adminMapper.selectOne(queryWrapper);
        return admin;
    }

    public List<Permission> findPermissionByAdminId(Long adminId) {
        return adminMapper.findPermissionByAdminId(adminId);
    }
}
