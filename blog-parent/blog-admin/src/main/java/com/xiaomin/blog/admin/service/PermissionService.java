package com.xiaomin.blog.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomin.blog.admin.mapper.PermissionMapper;
import com.xiaomin.blog.admin.model.params.PageParam;
import com.xiaomin.blog.admin.pojo.Permission;
import com.xiaomin.blog.admin.vo.PageResult;
import com.xiaomin.blog.admin.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 19:05
 */
@Service
public class PermissionService {
    @Resource
    private PermissionMapper permissionMapper;


    public Result listPermission(PageParam pageParam) {
        /**
         * 要的数据,管理台 表的所有字段 Permission
         * 分页查询
         */
        Page<Permission> page=new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNoneBlank(pageParam.getQueryString())){
            queryWrapper.eq("name",pageParam.getQueryString());
        }
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        PageResult<Permission> pageResult=new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());
        return Result.success(pageResult);
    }

    public Result add(Permission permission) {
        this.permissionMapper.insert(permission);
        return Result.success(null);
    }

    public Result update(Permission permission) {
        this.permissionMapper.updateById(permission);
        return Result.success(null);
    }

    public Result delete(Long id) {
        this.permissionMapper.deleteById(id);
        return Result.success(null);
    }
}
