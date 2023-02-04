package com.sjy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjy.blog.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:28
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findHotArticles(int limit);

    IPage<Article> listArticles(Page<Article> page, Long tagId, Long categoryId, String year, String month);
}
