package com.sjy.blog.service;

import com.sjy.blog.dao.pojo.Article;
import com.sjy.blog.dao.pojo.ArticleTag;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 21:25
 */
public interface ArticleTagService {
    void save(ArticleTag articleTag);

    List<ArticleTag> getArticleIdListByTagId(Long tagId);
}
