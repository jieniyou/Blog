package com.xiaomin.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomin.blog.dao.mapper.CategoryMapper;
import com.xiaomin.blog.dao.pojo.Category;
import com.xiaomin.blog.service.CategoryService;
import com.xiaomin.blog.vo.CategoryVo;
import com.xiaomin.blog.vo.Result;
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
 * @Date_Time: 2022/6/22 23:05
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Result categoryDetailById(Long id) {
        Category category=categoryMapper.selectById(id);
        return Result.success(copy(category));
    }

    @Override
    public Result findAllDetail() {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        //页面交互的对象
        return Result.success(copyList(categories));
    }

    @Override
    public Result findAll() {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id","category_name");
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        //页面交互的对象
        return Result.success(copyList(categories));
    }

    public CategoryVo copy(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
    public List<CategoryVo> copyList(List<Category> categoryList){
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(copy(category));
        }
        return categoryVoList;
    }
    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo=new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        categoryVo.setId(String.valueOf(category.getId()));
        return categoryVo;
    }
}
