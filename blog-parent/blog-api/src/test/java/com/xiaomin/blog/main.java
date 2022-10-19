package com.xiaomin.blog;

import com.xiaomin.blog.dao.mapper.ArticleMapper;
import com.xiaomin.blog.dao.pojo.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/15 14:30
 */
@SpringBootTest
public class main {
    @Resource
    private ArticleMapper articleMapper;
    @Test
    public void test1(){
        List<Article> articles = articleMapper.selectList(null);
        articles.forEach(System.out::println);
    }
}
