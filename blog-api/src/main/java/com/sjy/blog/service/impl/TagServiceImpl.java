package com.sjy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.TagMapper;
import com.sjy.blog.dao.pojo.Tag;
import com.sjy.blog.service.TagService;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/3 15:16
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 根绝文章id查tags
     * @param articleId
     * @return
     */
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tagList = tagMapper.selectTagsByArticleId(articleId);
        return convertList(tagList);
    }

    @Override
    public R findHotTags(int limit) {
        List<Long> tagIdList = tagMapper.findHotTagIds(limit);
        if(CollectionUtils.isEmpty(tagIdList)) {
            return R.success(Collections.emptyList());
        }
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Tag::getId, tagIdList);
        List<Tag> tagList = tagMapper.selectList(queryWrapper);
        return R.success(convertList(tagList));
    }

    @Override
    public R listAllTags() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        List<Tag> tagList = tagMapper.selectList(queryWrapper);
        return R.success(convertList(tagList));
    }

    @Override
    public R listAllTagsDetail() {
        List<Tag> tagList = tagMapper.selectList(null);
        return R.success(convertList(tagList));
    }

    @Override
    public R getTagDetailById(Long tagId) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tag::getId, tagId)
                .last("limit 1");
        Tag tag = tagMapper.selectOne(queryWrapper);
        return R.success(convert(tag));
    }

    private TagVo convert(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        if(tag.getId() != null) {
            tagVo.setId(String.valueOf(tag.getId()));
        }
        return tagVo;
    }

    private List<TagVo> convertList(List<Tag> tagList) {
        List<TagVo> tagVoList = new ArrayList<>();
        if(tagList != null && !tagList.isEmpty()) {
            for (Tag tag : tagList) {
                tagVoList.add(convert(tag));
            }
        }
        return tagVoList;
    }
}
