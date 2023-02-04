package com.sjy.blog.vo.params;

import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/4 19:42
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
