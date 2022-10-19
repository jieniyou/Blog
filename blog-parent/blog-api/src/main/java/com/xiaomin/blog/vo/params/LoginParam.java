package com.xiaomin.blog.vo.params;

import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 14:29
 */
@Data
public class LoginParam {

    private String account;
    private String password;

    private String nickname;
}
