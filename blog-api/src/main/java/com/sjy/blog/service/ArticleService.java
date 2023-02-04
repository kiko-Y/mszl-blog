package com.sjy.blog.service;

import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.ArticleParam;
import com.sjy.blog.vo.params.PageParam;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:39
 */
public interface ArticleService {
    R listAllArticles(PageParam pageParam);

    R findHotArticles(int limit);

    R findNewArticles(int limit);

    R listArchives();

    R findArticleById(Long id);

    Long getAuthorIdByArticleId(Long articleId);

    R publishArticle(ArticleParam articleParam);
}
