package com.sjy.blog.service;

import com.sjy.blog.dao.pojo.Category;

/**
 * @author: Kiko
 * @date: 2023/2/4 15:31
 */
public interface CategoryService {
    Category findCategoryById(Long id);
}
