package com.sjy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjy.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:30
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> selectTagsByArticleId(Long articleId);

    List<Long> findHotTagIds(int limit);
}
