package com.xiaomin.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:23
 */
@Data
public class ArticleVo {
    //@JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String title;
    private String summary;
    private Integer commentCounts;
    private Integer viewCounts;
    private Integer weight;
    /**
     * 创建时间
     */
    private String createdDate;
    private String author;
    private ArticleBodyVo body;
    private CategoryVo category;
    private List<TagVo> tags;
    private List<CategoryVo> categorys;

}
