package com.sjy.blog.service;

import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.CommentParam;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:11
 */
public interface CommentService {
    R listCommentsByArticleId(Long articleId);

    R addComment(CommentParam commentParam);
}
