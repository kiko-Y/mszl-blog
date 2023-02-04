package com.sjy.blog.controller;

import com.sjy.blog.service.CategoryService;
import com.sjy.blog.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kiko
 * @date: 2023/2/4 20:35
 */
@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public R listAllCategories() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/detail")
    public R listAllCategoriesDetail() {
        return categoryService.listAllCategoriesDetail();
    }
}
