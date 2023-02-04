package com.sjy.blog.controller;

import com.sjy.blog.service.ArticleService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.ArticleParam;
import com.sjy.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:30
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {

    /**
     * 获取首页文章列表
     * @param pageParam
     */

    @Autowired
    private ArticleService articleService;


    @PostMapping
    public R listAllArticles(@RequestBody PageParam pageParam) {
        return articleService.listAllArticles(pageParam);
    }

    @PostMapping("/hot")
    public R findHotArticles() {
        int limit = 3;
        return articleService.findHotArticles(limit);
    }

    @PostMapping("/new")
    public R findNewArticles() {
        int limit = 3;
        return articleService.findNewArticles(limit);
    }

    @PostMapping("/listArchives")
    public R listArchives() {
        return articleService.listArchives();
    }

    @PostMapping("/view/{id}")
    public R articleDetail(@PathVariable("id") Long id) {
        return articleService.findArticleById(id);
    }

    @PostMapping("/publish")
    public R publishArticle(@RequestBody ArticleParam articleParam) {
        return articleService.publishArticle(articleParam);
    }
}
