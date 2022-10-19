package com.xiaomin.blog.admin.service;

import com.xiaomin.blog.admin.pojo.Admin;
import com.xiaomin.blog.admin.pojo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 21:45
 */
@Service
public class AuthService {

    @Autowired
    private AdminService adminService;
    public boolean auth(HttpServletRequest request, Authentication authentication){
        //权限认证
        //请求路径
        String requestURI = request.getRequestURI();
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)){
            //未登录
            return false;
        }
        UserDetails userDetails= (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.findAdminByUsername(username);
        if (admin == null) {
            return false;
        }
        if (admin.getId()==1){
            //超级管理员
            return true;
        }
        Long id = admin.getId();
        List<Permission> permissionList=this.adminService.findPermissionByAdminId(id);
        requestURI= StringUtils.split(requestURI,'?')[0];
        for (Permission permission : permissionList) {
            if (requestURI.equals(permission.getPath())){
                return true;
            }
        }
        return false;

    }
}
