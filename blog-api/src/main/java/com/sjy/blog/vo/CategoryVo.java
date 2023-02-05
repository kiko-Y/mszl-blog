package com.sjy.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:55
 */
@Data
public class CategoryVo {
//    @JsonSerialize(using = ToStringSerializer.class)
//    private Long id;
    private String id;

    private String avatar;

    private String categoryName;

    private String description;
}
