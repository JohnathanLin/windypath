package com.windypath.blog.modules.admin.datacontrol.pojo;

/**
 * 博客文章（带正文）
 *
 */
public class WpArticleWithBLOBs extends WpArticle {
    /**
     * 文章正文（中文）
     */
    private String contentCn;
    /**
     * 文章正文（英文）
     */
    private String contentEn;
}