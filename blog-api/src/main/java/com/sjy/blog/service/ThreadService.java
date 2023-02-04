package com.sjy.blog.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.sjy.blog.dao.mapper.ArticleMapper;
import com.sjy.blog.dao.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: Kiko
 * @date: 2023/2/4 15:47
 */
@Component
public class ThreadService {

    @Autowired
    private ArticleMapper articleMapper;

    @Async("taskExecutor")
    public void updateArticleViewCount(Article article) {
        // 乐观锁实现
        int update;
        do {
            int viewCounts = article.getViewCounts();
            LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Article::getId, article.getId())
                    .eq(Article::getViewCounts, viewCounts)
                    .set(Article::getViewCounts, viewCounts + 1);
            update = articleMapper.update(null, updateWrapper);
        } while(update == 0);
    }
}
