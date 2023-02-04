package com.sjy.blog.vo.params;

import lombok.Data;

/**
 * @author: Kiko
 * @date: 2023/2/3 14:32
 */
@Data
public class PageParam {
    private Integer page = 1;

    private Integer pageSize = 10;

    private Long categoryId;

    private Long tagId;

    private String year;

    private String month;

//    public String getMonth(){
//        if (this.month != null && this.month.length() == 1){
//            return "0"+this.month;
//        }
//        return this.month;
//    }
}
