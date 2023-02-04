package com.sjy.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sjy.blog.dao.dos.Archive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 19:06
 */
@Mapper
public interface ArchiveMapper extends BaseMapper<Archive> {
    List<Archive> listArchives();
}
