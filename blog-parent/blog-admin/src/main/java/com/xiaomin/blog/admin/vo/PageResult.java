package com.xiaomin.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 19:11
 */
@Data
public class PageResult<T> {
    private List<T> list;
    private Long total;
}
