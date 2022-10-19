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
        //�����˲������̳߳�ִ��,����Ӱ��ԭ�е����߳�
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts+1);
        UpdateWrapper<Article> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",article.getId());
        //����һ�� Ϊ���ڶ��̵߳Ļ����� �̰߳�ȫ
        updateWrapper.eq("view_counts",viewCounts);
        //update article set view_counts = 100 where view_counts=99 and id = 11
        articleMapper.update(articleUpdate,updateWrapper);
        //try {
        //    Thread.sleep(5000);
        //    System.out.println("���������...");
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }
}
