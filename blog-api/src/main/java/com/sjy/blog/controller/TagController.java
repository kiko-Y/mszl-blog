package com.sjy.blog.controller;

import com.sjy.blog.service.TagService;
import com.sjy.blog.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kiko
 * @date: 2023/2/3 15:49
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public R findHotTags() {
        int limit = 2;
        return tagService.findHotTags(limit);
    }

    @GetMapping
    public R listAllTags() {
        return tagService.listAllTags();
    }
}
