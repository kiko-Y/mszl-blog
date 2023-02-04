package com.sjy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.ArticleTagMapper;
import com.sjy.blog.dao.pojo.Article;
import com.sjy.blog.dao.pojo.ArticleTag;
import com.sjy.blog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 21:26
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Override
    public void save(ArticleTag articleTag) {
        articleTagMapper.insert(articleTag);
    }

    @Override
    public List<ArticleTag> getArticleIdListByTagId(Long tagId) {
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ArticleTag::getArticleId)
                        .eq(ArticleTag::getTagId, tagId);
        return articleTagMapper.selectList(queryWrapper);
    }
}
