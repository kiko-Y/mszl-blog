package com.sjy.blog.controller;

import com.sjy.blog.service.CommentService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create/change")
    public R comment(@RequestBody CommentParam commentParam) {
        return commentService.addComment(commentParam);
    }
}
