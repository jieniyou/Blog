package com.xiaomin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomin.blog.dao.dos.Archives;
import com.xiaomin.blog.dao.mapper.ArticleBodyMapper;
import com.xiaomin.blog.dao.mapper.ArticleMapper;
import com.xiaomin.blog.dao.mapper.ArticleTagMapper;
import com.xiaomin.blog.dao.pojo.Article;
import com.xiaomin.blog.dao.pojo.ArticleBody;
import com.xiaomin.blog.dao.pojo.ArticleTag;
import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.service.*;
import com.xiaomin.blog.utils.UserThreadLocal;
import com.xiaomin.blog.vo.ArticleBodyVo;
import com.xiaomin.blog.vo.ArticleVo;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.TagVo;
import com.xiaomin.blog.vo.params.ArticleParam;
import com.xiaomin.blog.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:11
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Resource
    private ArticleTagMapper articleTagMapper;
    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page=new Page<>(pageParams.getPage(),pageParams.getPageSize());

        IPage<Article> articleIPage = articleMapper.listArticle(
                page,
                pageParams.getCategoryId(),
                pageParams.getTagId(),
                pageParams.getYear(),
                pageParams.getMonth());
        List<Article> records = articleIPage.getRecords();
        return Result.success(copyList(records,true,true));
    }
    //@Override
    //public Result listArticle(PageParams pageParams) {
    //    /**
    //     * 1.分页查询article数据库表
    //     */
    //    Page<Article> page=new Page<>(pageParams.getPage(),pageParams.getPageSize());
    //    System.out.println("page===>"+page);
    //    //LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
    //    QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
    //    System.out.println("queryWrapper=====>"+queryWrapper);
    //
    //    // and category_id = #{CategoryId}
    //    if (pageParams.getCategoryId()!=null){
    //        queryWrapper.eq("category_id",pageParams.getCategoryId());
    //    }
    //
    //    List<Long> articleIdList=new ArrayList<>();
    //    if (pageParams.getTagId()!=null){
    //        //加入标签条件查询
    //        //article表中并没有tag字段 一篇文章有多个标签
    //        //article_tag article_id 1:n tag_id
    //        QueryWrapper<ArticleTag> articleTagQueryWrapper=new QueryWrapper<>();
    //        articleTagQueryWrapper.eq("tag_id",pageParams.getTagId());
    //        List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagQueryWrapper);
    //        for (ArticleTag articleTag : articleTags) {
    //            articleIdList.add(articleTag.getArticleId());
    //        }
    //        if (articleIdList.size()>0){
    //            // and id in (1,2,3)
    //            queryWrapper.in("id",articleIdList);
    //        }
    //    }
    //
    //    //是否置顶进行排序
    //    //order by create_date desc
    //    queryWrapper.orderByDesc("weight");
    //    System.out.println("queryWrapper1=====>"+queryWrapper);
    //    queryWrapper.orderByDesc("create_date");
    //    System.out.println("queryWrapper2=====>"+queryWrapper);
    //    Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
    //    List<Article> records = articlePage.getRecords();
    //    //能直接返回吗?很明显不能
    //    List<ArticleVo> articleVoList=copyList(records,true,true);//,true,true
    //    return Result.success(articleVoList);
    //}

    @Override
    public Result hotArticle(int limit) {
        //LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("view_counts");
        queryWrapper.select("id","title");
        queryWrapper.last("limit "+limit);
        //select id,title from article order by view_counts desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result newArticles(int limit) {
        //LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        QueryWrapper<Article> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("create_date");
        queryWrapper.select("id","title");
        queryWrapper.last("limit "+limit);
        //select id,title from article order by create_date desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Autowired
    private ThreadService threadService;

    @Override
    public Result publish(ArticleParam articleParam) {
        //此接口 要加入到登录拦截当中
        SysUser sysUser= UserThreadLocal.get();
        /**
         * 1.发布文章 目的 构建 Article 对象
         * 2.作者id 当前登录用户
         * 3.标签 要将标签加入到关联列表当中
         * 4.body 内容存储 article bodyId
         */
        Article article=new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(Article.Article_Common);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
        //插入之后 会生成一个文章id
        this.articleMapper.insert(article);
        //tag
        List<TagVo> tags = articleParam.getTags();
        if (tags!=null){
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                ArticleTag articleTag=new ArticleTag();
                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }
        }
        //body
        ArticleBody articleBody=new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        Map<String,String> map=new HashMap<>();
        map.put("id",article.getId().toString());
        System.err.println(map);
        return Result.success(map);
    }

    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1.根据id查询 文章信息
         * 2.根据bodyId和categoryId 去做关联查询
         */
        Article article=this.articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true,true,true);
        //查看完文章了,新增阅读数,有没有问题呢?
        //查看完文章之后,本应该直接返回数据了,这时候做了一个更新操作,更新时加写锁,阻塞其他的读操作,性能会比较低
        //更新 增加了此次接口的耗时 如果更新一旦出问题,不能影响查看文章的操作
        //线程池  可以把更新操作扔到线程池中去执行,和主线程就不相关了
        threadService.updateArticleViewCount(articleMapper,article);

        return Result.success(articleVo);
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor) {//,boolean isTag,boolean isAuthor
        List<ArticleVo> articleVoList=new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,false,false));//,isTag,isAuthor
        }
        return articleVoList;
    }
    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory) {//,boolean isTag,boolean isAuthor
        List<ArticleVo> articleVoList=new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));//,isTag,isAuthor
        }
        return articleVoList;
    }

    @Autowired
    private CategoryService categoryService;

    private ArticleVo copy(Article article,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory){//,boolean isTag,boolean isAuthor
        ArticleVo articleVo=new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        BeanUtils.copyProperties(article,articleVo);

        articleVo.setCreatedDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        //并不是所有的接口都需要标签,作者信息
        if(isTag){
            long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor){
            long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (isBody){
            Long bodyId=article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if (isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    @Resource
    private ArticleBodyMapper articleBodyMapper;
    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo=new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
