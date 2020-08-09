package com.windypath.blog.modules.admin.datacontrol.dao;

import com.windypath.blog.modules.admin.datacontrol.pojo.WpArticle;
import com.windypath.blog.modules.admin.datacontrol.pojo.WpArticleWithBLOBs;
import com.windypath.blog.modules.admin.datacontrol.vo.WpArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章
 *
* Created by Mybatis Generator 2020/08/08
*/
@Mapper
public interface WpArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WpArticleWithBLOBs entity);

    int insertSelective(WpArticle entity);

    WpArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WpArticle entity);

    int updateByPrimaryKeyWithBLOBs(WpArticleWithBLOBs entity);

    int updateByPrimaryKey(WpArticle entity);

    /**
     * 分页查询文章列表
     *
     * @param wpArticleVo 查询条件
     * @return
     */
    List<WpArticleVo> findByPage(WpArticleVo wpArticleVo);

    /**
     * 查询id对应的文章详细信息
     *
     * @param id
     * @return
     */
    WpArticleVo getById(@Param("id") Integer id);
}