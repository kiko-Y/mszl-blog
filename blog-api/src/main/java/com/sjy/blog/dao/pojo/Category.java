package com.sjy.blog.dao.pojo;

import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/4 15:12
 */
@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}