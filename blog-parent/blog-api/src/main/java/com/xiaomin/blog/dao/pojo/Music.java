package com.xiaomin.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/7/3 12:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private String name;
    private String url;
    private String artist;
    private String cover;
    private String lrc;
}
