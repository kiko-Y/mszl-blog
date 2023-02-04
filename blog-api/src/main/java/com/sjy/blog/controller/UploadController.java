package com.sjy.blog.controller;

import com.sjy.blog.utils.QiniuUtils;
import com.sjy.blog.vo.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author: Kiko
 * @date: 2023/2/4 22:00
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public R upload(@RequestParam("image") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();


        // 获得唯一的文件名
        String fileName = StringUtils.joinWith("",
                UUID.randomUUID().toString(), ".",
                StringUtils.substringAfterLast(originalFilename, "."));

        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            return R.success(QiniuUtils.url + fileName);
        }
        return R.fail(20001,"上传失败");
    }
}
