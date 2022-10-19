package com.xiaomin.blog.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomin.blog.admin.pojo.Admin;
import com.xiaomin.blog.admin.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 21:34
 */
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from xm_permission where id in (select permission_id from xm_admin_permission where admin_id=#{adminId})")
    List<Permission> findPermissionByAdminId(Long adminId);
}
