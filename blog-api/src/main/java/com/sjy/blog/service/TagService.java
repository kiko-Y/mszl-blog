package com.sjy.blog.service;

import com.sjy.blog.vo.R;
import com.sjy.blog.vo.TagVo;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 15:16
 */
public interface TagService {
    List<TagVo> findTagsByArticleId(Long articleId);

    R findHotTags(int limit);

    R listAllTags();
}
