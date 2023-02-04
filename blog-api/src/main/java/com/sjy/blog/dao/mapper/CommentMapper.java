package com.sjy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjy.blog.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:11
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
