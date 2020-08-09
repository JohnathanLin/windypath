package com.windypath.blog.common.vo;

import lombok.Data;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 20:07
 */
@Data
public class PageVo {
    /**
     * 页码，从1开始
     */
    private int page = 1;
    /**
     * 页面大小
     */
    private int size = 10;
    /**
     * 排序
     */
    private String orderBy;
}
