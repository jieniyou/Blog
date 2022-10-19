package com.xiaomin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaomin.blog.dao.mapper.ArticleMapper;
import com.xiaomin.blog.dao.mapper.CommentMapper;
import com.xiaomin.blog.dao.pojo.Article;
import com.xiaomin.blog.dao.pojo.Comment;
import com.xiaomin.blog.dao.pojo.SysUser;
import com.xiaomin.blog.service.CommentsService;
import com.xiaomin.blog.service.SysUserService;
import com.xiaomin.blog.utils.UserThreadLocal;
import com.xiaomin.blog.vo.CommentVo;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.UserVo;
import com.xiaomin.blog.vo.params.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 15:28
 */
@Service
public class CommentsServiceImpl implements CommentsService {
    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService sysUserService;
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public Result commentsByArticleId(Long id) {
        /**
         * 1.根据文章id查询评论列表 从comment表中查询
         * 2.根据作者的id查询作者的信息
         * 3.判断如果level=1要去查询它有没有子评论
         * 4.如果有 根据评论id进行查询(parent_id)
         */
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("article_id",id);
        queryWrapper.eq("level",1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList=copyList(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        UpdateWrapper<Article> updateWrapper = Wrappers.update();
        updateWrapper.eq("id",comment.getArticleId());
        updateWrapper.setSql(true,"comment_counts=comment_counts+1");
        this.articleMapper.update(null,updateWrapper);
        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList=new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo=new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        commentVo.setId(String.valueOf(comment.getId()));
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        //子评论
        Integer level = comment.getLevel();
        if (1==level){
            Long id = comment.getId();
            List<CommentVo> commentVoList=findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        //to User 给谁评论
        if (level>1){
            Long Uid = comment.getToUid();
            UserVo toUserVo = this.sysUserService.findUserVoById(Uid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        queryWrapper.eq("level",2);
        return copyList(commentMapper.selectList(queryWrapper));
    }
}
