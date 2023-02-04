package com.sjy.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:50
 */
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVo body;

    private List<TagVo> tags;

    private List<CategoryVo> categories;

}
