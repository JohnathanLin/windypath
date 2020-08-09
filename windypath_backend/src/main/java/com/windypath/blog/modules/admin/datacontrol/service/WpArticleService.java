package com.windypath.blog.modules.admin.datacontrol.service;

import com.windypath.blog.common.model.PageModel;
import com.windypath.blog.common.vo.PageVo;
import com.windypath.blog.modules.admin.datacontrol.pojo.WpArticle;
import com.windypath.blog.modules.admin.datacontrol.vo.WpArticleVo;

/**
 * 博客文章
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 20:11
 */
public interface WpArticleService {
    /**
     * 分页查询
     *
     * @param pageVo 分页信息
     * @param wpArticleVo 查询信息
     * @return
     */
    PageModel<WpArticleVo> findByPage(PageVo pageVo, WpArticleVo wpArticleVo);

    /**
     * 新增文章
     *
     * @param wpArticleVo 文章信息
     */
    void save(WpArticleVo wpArticleVo);

    /**
     * 编辑文章
     *
     * @param wpArticleVo 文章信息
     */
    void edit(WpArticleVo wpArticleVo);
    /**
     * 根据ID获取文章信息
     *
     * @param id id
     * @return
     */
    WpArticleVo getById(Integer id);

    /**
     * 根据ID删除文章
     *
     * @param id id
     */
    void delete(Integer id);
}
