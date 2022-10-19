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
//对加了@Controller注解的方法进行拦截处理  AOP的实现
@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理,处理Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody   //返回json数据
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
