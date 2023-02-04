package com.sjy.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Kiko
 * @date: 2023/2/4 21:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long TagId;
}
