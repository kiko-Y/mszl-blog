package com.sjy.blog.vo.params;

import com.sjy.blog.vo.CategoryVo;
import com.sjy.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * 用于接收发布文章的参数
 * @author: Kiko
 * @date: 2023/2/4 20:55
 */

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}