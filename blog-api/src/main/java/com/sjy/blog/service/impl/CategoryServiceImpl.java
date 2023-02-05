package com.sjy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.CategoryMapper;
import com.sjy.blog.dao.pojo.Category;
import com.sjy.blog.service.CategoryService;
import com.sjy.blog.vo.CategoryVo;
import com.sjy.blog.vo.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 15:31
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public R listAllCategories() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Category::getId, Category::getCategoryName);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return R.success(convertList(categories));
    }

    @Override
    public R listAllCategoriesDetail() {
        List<Category> categories = categoryMapper.selectList(null);
        return R.success(convertList(categories));
    }

    @Override
    public R getCategoryDetailById(Long categoryId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, categoryId)
                .last("limit 1");
        Category category = categoryMapper.selectOne(queryWrapper);
        return R.success(convert(category));
    }


    private CategoryVo convert(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        if(category.getId() != null) {
            categoryVo.setId(String.valueOf(category.getId()));
        }
        return categoryVo;
    }

    private List<CategoryVo> convertList(List<Category> categoryList) {
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryVoList.add(convert(category));
        }
        return categoryVoList;
    }
}
