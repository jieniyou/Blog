package com.xiaomin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomin.blog.dao.mapper.TagMapper;
import com.xiaomin.blog.dao.pojo.Tag;
import com.xiaomin.blog.service.TagService;
import com.xiaomin.blog.vo.Result;
import com.xiaomin.blog.vo.TagVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: XiaoMin
 * @PRODUCT_NAME: IntelliJ IDEA
 * @PROJECT_NAME: blog-parent
 * @Date_Time: 2022/6/10 19:56
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    public TagVo copy(Tag tag){
        TagVo tagVo=new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        tagVo.setId(String.valueOf(tag.getId()));
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList=new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }
    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        //mybatisplus 无法进行多表查询
        List<Tag> tags=tagMapper.findTagsByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag=tagMapper.selectById(id);
        return Result.success(copy(tag));
    }

    @Override
    public Result findAllDetail() {
        QueryWrapper<Tag> queryWrapper=new QueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);

        return Result.success(copyList(tags));
    }

    @Override
    public Result findAll() {
        QueryWrapper<Tag> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id","tag_name");
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result hots(int limit) {
        /**
         * 1.标签所拥有的文章数量最多   最热标签
         * 2.查询 根据tag_id 分组 计数,从大到小 排列 取前 limit 个
         */
        List<Long> tagIds=tagMapper.findHotsTagIds(limit);
        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        //需求的是 tagId 和 tagName Tag对象
        //select * from tag where id in (1,2,3,4)
        List<Tag>tagList=tagMapper.findTagsByTagIds(tagIds);
        return Result.success(tagList);
    }
}
