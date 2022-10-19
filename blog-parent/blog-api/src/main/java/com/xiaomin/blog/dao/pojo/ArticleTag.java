package com.xiaomin.blog.dao.pojo;

import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 18:49
 */
@Data
public class ArticleTag {
    private Long id;
    private Long articleId;
    private Long tagId;
}
