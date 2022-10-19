package com.xiaomin.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiaomin.blog.dao.mapper.ArticleMapper;
import com.xiaomin.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/22 23:34
 */
@Component
public class ThreadService {
        //期望此操作在线程池执行,不会影响原有的主线程
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts+1);
        UpdateWrapper<Article> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",article.getId());
        //设置一个 为了在多线程的环境下 线程安全
        updateWrapper.eq("view_counts",viewCounts);
        //update article set view_counts = 100 where view_counts=99 and id = 11
        articleMapper.update(articleUpdate,updateWrapper);
        //try {
        //    Thread.sleep(5000);
        //    System.out.println("更新完成了...");
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }
}
