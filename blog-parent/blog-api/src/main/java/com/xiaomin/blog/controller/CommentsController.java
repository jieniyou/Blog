package com.xiaomin.blog.controller;

import com.xiaomin.blog.service.CommentsService;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 15:23
 */
@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    // /comments/article/{id}
    @GetMapping("article/{id}")
    public Result comments(@PathVariable Long id){
        return commentsService.commentsByArticleId(id);
    }
    @PostMapping("create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentsService.comment(commentParam);
    }
}
