package com.xiaomin.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:00
 */
@Data
@AllArgsConstructor
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        System.out.println("data===>"+data);
        return new Result(true,200,"success",data);
    }
    public static Result fail(int code,String msg){
        return new Result(false,code,msg,null);
    }
}
