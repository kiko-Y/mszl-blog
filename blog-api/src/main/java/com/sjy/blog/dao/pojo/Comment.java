package com.sjy.blog.dao.pojo;

import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:09
 */

@Data
public class Comment {

    private Long id;

    private String content;

    private Long authorId;

    private Long createDate;

    private Long articleId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
