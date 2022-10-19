package com.xiaomin.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomin.blog.dao.pojo.Tag;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 18:53
 */
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询标签列表
     * @param articleId
     * @return
     */
    List<Tag> findTagsByArticleId(Long articleId);

    /**
     * 查询最热的标签 前n条
     * @param limit
     * @return
     */
    List<Long> findHotsTagIds(int limit);


    List<Tag> findTagsByTagIds(List<Long> tagIds);
}
