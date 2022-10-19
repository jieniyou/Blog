package com.xiaomin.blog.admin.model.params;

import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 19:04
 */
@Data
public class PageParam {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
}
