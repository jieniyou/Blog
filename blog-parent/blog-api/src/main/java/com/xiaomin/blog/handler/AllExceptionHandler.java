package com.xiaomin.blog.handler;

import com.xiaomin.blog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 10:09
 */
//�Լ���@Controllerע��ķ����������ش���  AOP��ʵ��
@ControllerAdvice
public class AllExceptionHandler {
    //�����쳣����,����Exception.class���쳣
    @ExceptionHandler(Exception.class)
    @ResponseBody   //����json����
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"ϵͳ�쳣");
    }
}
