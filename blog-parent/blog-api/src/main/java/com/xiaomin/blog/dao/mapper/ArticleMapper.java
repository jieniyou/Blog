package com.xiaomin.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomin.blog.dao.dos.Archives;
import com.xiaomin.blog.dao.pojo.Article;
import com.xiaomin.blog.vo.ArticleVo;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.params.PageParams;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 18:48
 */
public interface ArticleMapper extends BaseMapper<Article> {
    List<Archives> listArchives();

    IPage<Article> listArticle(Page<Article> page,
                               Long categoryId,
                               Long tagId,
                               String year,
                               String month);
}
