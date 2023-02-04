package com.sjy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjy.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findHotArticles(int limit);
}
