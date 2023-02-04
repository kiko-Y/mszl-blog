package com.sjy.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:12
 */

@Data
public class CommentVo  {

    private Long id;

    private String content;

    private UserVo author;

    private String createDate;

    private List<CommentVo> childrens;

    private UserVo toUser;

    private Integer level;
}

