package com.sjy.blog.service.impl;

import com.sjy.blog.dao.mapper.CategoryMapper;
import com.sjy.blog.dao.pojo.Category;
import com.sjy.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
