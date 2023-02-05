package com.sjy.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:12
 */

@Data
public class CommentVo  {

    // 防止前端精度损失 把Id转为String
//    @JsonSerialize(using = ToStringSerializer.class)
//    private Long id;

    private String id;

    private String content;

    private UserVo author;

    private String createDate;

    private List<CommentVo> childrens;

    private UserVo toUser;

    private Integer level;
}

