package com.sjy.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Kiko
 * @date: 2023/2/4 15:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}