package com.xiaomin.blog.service;

import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.params.CommentParam;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 15:28
 */
public interface CommentsService {
    /**
     * ��������id��ѯ���е������б�
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    Result comment(CommentParam commentParam);
}
