package com.xiaomin.blog.service;

import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.TagVo;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:54
 */
public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    Result hots(int limit);

    /**
     * ��ѯ���е����±�ǩ
     * @return
     */
    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);
}
