package com.windypath.blog.modules.admin.datacontrol.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 20:09
 */
@Data
public class WpArticleVo {
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
    /* ==== 查询条件 ==== */
    /**
     * 分类ID列表
     */
    private List<Integer> categoryIdList;
    /* ===== 查询结果 =====*/
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 作者名称
     */
    private String username;
}
