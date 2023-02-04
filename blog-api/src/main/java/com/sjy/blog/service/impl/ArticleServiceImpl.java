package com.sjy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjy.blog.dao.dos.Archive;
import com.sjy.blog.dao.mapper.ArchiveMapper;
import com.sjy.blog.dao.mapper.ArticleMapper;
import com.sjy.blog.dao.pojo.Article;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.ArticleService;
import com.sjy.blog.service.SysUserService;
import com.sjy.blog.service.TagService;
import com.sjy.blog.vo.ArchiveVo;
import com.sjy.blog.vo.ArticleVo;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.TagVo;
import com.sjy.blog.vo.params.PageParam;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:40
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArchiveMapper archiveMapper;


    /**
     * 获取首页所有文章信息
     * @param pageParam
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public R listAllArticles(PageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate, Article::getWeight);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> articleList = articlePage.getRecords();
        List<ArticleVo> articleVoList = convertList(articleList, true, true, false);
        return R.success(articleVoList);
    }

    /**
     * 查询最热文章
     * @return
     */
    @Override
    public R findHotArticles(int limit) {
        List<Article> articleList = articleMapper.findHotArticles(limit);
        return R.success(convertList(articleList, false, false, false));
    }

    /**
     * 查询最新文章
     * @param limit
     * @return
     */
    @Override
    public R findNewArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate)
                .select(Article::getId, Article::getTitle)
                .last(" limit " + limit);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return R.success(convertList(articleList, false, false, false));
    }

    /**
     * 文章归档
     * @return
     */
    @Override
    public R listArchives() {
        List<Archive> archiveList = archiveMapper.listArchives();
        return R.success(convertList(archiveList));
    }

    private ArchiveVo convert(Archive archive) {
        ArchiveVo archiveVo = new ArchiveVo();
        BeanUtils.copyProperties(archive, archiveVo);
        return archiveVo;
    }

    private List<ArchiveVo> convertList(List<Archive> archiveList) {
        List<ArchiveVo> archiveVoList = new ArrayList<>();
        if(archiveList != null && !archiveList.isEmpty()) {
            for (Archive archive : archiveList) {
                archiveVoList.add(convert(archive));
            }
        }
        return archiveVoList;
    }

    private ArticleVo convert (Article article, boolean needTags, boolean needAuthor, boolean needBody) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        if(Objects.nonNull(article.getCreateDate())) {
            articleVo.setCreateDate(new LocalDateTime(article.getCreateDate())
                    .toString("yyyy-MM-dd HH:mm"));
        }
        if(needTags) {
            Long articleId = article.getId();
            List<TagVo> tags = tagService.findTagsByArticleId(articleId);
            articleVo.setTags(tags);
        }

        if (needAuthor) {
            Long authorId = article.getAuthorId();
            SysUser user = sysUserService.findSysUserById(authorId);
            articleVo.setAuthor(Objects.isNull(user) ? "defaultName" : user.getNickname());
        }

        if(needBody) {

        }
        return articleVo;
    }

    private List<ArticleVo> convertList(List<Article> articleList,
                                        boolean needTags, boolean needAuthor, boolean needBody) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        if(articleList != null && !articleList.isEmpty()) {
            for (Article article : articleList) {
                articleVoList.add(convert(article, needTags, needAuthor, needBody));
            }
        }
        return articleVoList;
    }
}
