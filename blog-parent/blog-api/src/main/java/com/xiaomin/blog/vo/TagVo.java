package com.xiaomin.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:28
 */
@Data
public class TagVo {
    //@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String tagName;
    private String avatar;
}
