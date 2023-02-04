package com.sjy.blog.controller;

import com.sjy.blog.service.CommentService;
import com.sjy.blog.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:10
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/article/{id}")
    public R listComments(@PathVariable("id") Long articleId) {
        return commentService.listCommentsByArticleId(articleId);
    }
}
