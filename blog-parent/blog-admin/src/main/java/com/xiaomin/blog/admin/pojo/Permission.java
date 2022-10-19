package com.xiaomin.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/24 19:09
 */
@Data
public class Permission {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String path;
    private String description;
}
