package com.windypath.blog.modules.admin.datacontrol.pojo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 博客文章
 *
* Created by Mybatis Generator 2020/08/08
*/
@Getter
@Setter
@ToString
public class WpArticle {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 文章标题（中文）
     */
    private String titleCn;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 文章标题（英文）
     */
    private String titleEn;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 文章状态（0 正常 -1草稿 1 隐藏）
     */
    private Integer status;
    /**
     * 评论数
     */
    private Integer commentNum;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 文章头图
     */
    private String picture;
    /**
     * 是否允许评论（0 允许 1 不允许）
     */
    private Integer allowComment;
    /**
     * 文章正文（中文）
     */
    private String contentCn;
    /**
     * 文章正文（英文）
     */
    private String contentEn;
}