package com.xiaomin.blog.controller;

import com.xiaomin.blog.utils.QiNiuYunUtils;
import com.xiaomin.blog.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/23 21:48
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private QiNiuYunUtils qiNiuYunUtils;
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file){
        //原始文件名称 比如aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件 上传到哪? 七牛云 云服务器 按量付费 速度快 把图片发放到离用户最近的服务器上
        //可以降低我们自身应用服务器的资源 带宽消耗
        boolean upload = qiNiuYunUtils.upload(file, fileName);
        if (upload){
            return Result.success(QiNiuYunUtils.url+fileName);
        }
        return Result.fail(20001,"上传失败");

    }
}
