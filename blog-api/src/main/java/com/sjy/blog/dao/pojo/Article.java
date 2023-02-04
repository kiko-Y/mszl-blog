package com.sjy.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private Integer weight;


    /**
     * 创建时间
     */
    private Long createDate;
}
