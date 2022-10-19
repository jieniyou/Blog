package com.xiaomin.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 22:49
 */
@Data
public class CategoryVo {
    //@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String avatar;
    private String categoryName;
    private String description;
}
