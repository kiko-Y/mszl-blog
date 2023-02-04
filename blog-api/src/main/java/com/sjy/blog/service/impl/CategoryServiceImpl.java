package com.sjy.blog.service.impl;

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
        List<Category> categories = categoryMapper.selectList(null);
        return R.success(convertList(categories));
    }

    private CategoryVo convert(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
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
