package com.sjy.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sjy.blog.dao.mapper.CommentMapper;
import com.sjy.blog.dao.pojo.Comment;
import com.sjy.blog.dao.pojo.SysUser;
import com.sjy.blog.service.ArticleService;
import com.sjy.blog.service.CommentService;
import com.sjy.blog.service.SysUserService;
import com.sjy.blog.utils.UserThreadLocal;
import com.sjy.blog.vo.CommentVo;
import com.sjy.blog.vo.R;
import com.sjy.blog.vo.UserVo;
import com.sjy.blog.vo.params.CommentParam;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Kiko
 * @date: 2023/2/4 17:11
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ArticleService articleService;

    @Override
    public R listCommentsByArticleId(Long articleId) {
        // 1. 根据文章id查询评论列表
        // 2. 根据作者id查询作者的信息
        // 3. 如果level == 1 查询有没有子评论
        // 4. 如果有 根据评论id进行查询(parent_id)
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getLevel, 1)
                .eq(Comment::getArticleId, articleId);
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList = convertList(commentList);
        return R.success(commentVoList);
    }

    @Override
    public R addComment(CommentParam commentParam) {
        SysUser user = UserThreadLocal.get();
        Long parent = commentParam.getParent();
        String content = commentParam.getContent();
        Long toUserId = commentParam.getToUserId();
        Long articleId = commentParam.getArticleId();
        log.info("{}", commentParam);
        Comment comment = new Comment();
        comment.setId(null);
        comment.setContent(content);
        comment.setAuthorId(user.getId());
        comment.setCreateDate(System.currentTimeMillis());
        comment.setArticleId(articleId);
        comment.setParentId(parent == null ? 0 : parent);
        comment.setToUid(toUserId == null ? 0 : toUserId);
        comment.setLevel((parent == null || parent == 0) ? 1 : 2);
        commentMapper.insert(comment);
        return R.success(null);
    }

    private List<CommentVo> convertList(List<Comment> commentList) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : commentList) {
            commentVoList.add(convert(comment));
        }
        return commentVoList;
    }
    private CommentVo convert(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        if(comment.getId() != null) {
            commentVo.setId(String.valueOf(comment.getId()));
        }
        commentVo.setCreateDate(new LocalDateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        commentVo.setAuthor(convert(sysUserService.findSysUserById(comment.getAuthorId())));
        if(comment.getToUid() != null) {
            Long toUid = comment.getToUid();
            commentVo.setToUser(convert(sysUserService.findSysUserById(toUid)));
        }
        if(comment.getLevel() == 1) {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getParentId, comment.getId());
            List<Comment> commentList = commentMapper.selectList(queryWrapper);
            List<CommentVo> commentVoList = convertList(commentList);
            commentVo.setChildrens(commentVoList);
        } else {
            commentVo.setChildrens(new ArrayList<>());
        }
        return commentVo;

    }

    private UserVo convert(SysUser user) {
        if(user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        if(user.getId() != null) {
            userVo.setId(String.valueOf(user.getId()));
        }
        return userVo;
    }
}
