package com.xiaomin.blog.handler;

import com.alibaba.fastjson.JSON;
import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.service.LoginService;
import com.xiaomin.blog.utils.UserThreadLocal;
import com.xiaomin.blog.vo.ErrorCode;
import com.xiaomin.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 21:44
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //��ִ��controller����(Handler)֮ǰ����ִ��
        /**
         * 1.��Ҫ�ж� ����Ľӿ�·�� �Ƿ�ΪHandlerMethod (controller����)
         * 2.�ж� token �Ƿ�Ϊ��,���Ϊ�� δ��¼
         * 3.��� token ��Ϊ��,��½��֤ loginService checkToken
         * 4.�����֤�ɹ� ���м���
         */
        if (!(handler instanceof HandlerMethod)){
            //handler ������ RequestResourceHandler, springboot ������ʾ�̬��ԴĬ��ȥclasspath�µ�staticĿ¼ȥ��ѯ
            return true;
        }
        String token = request.getHeader("Authorization");

        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(),"δ��¼");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser==null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(),"δ��¼");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //��¼��֤�ɹ�,����
        //��ϣ����controller�� ֱ�ӻ�ȡ�û�����Ϣ ��ô��ȡ?
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //�����ɾ�� ThreadLocal���������Ϣ �����ڴ�й¶�ķ���
        UserThreadLocal.remove();
    }
}
