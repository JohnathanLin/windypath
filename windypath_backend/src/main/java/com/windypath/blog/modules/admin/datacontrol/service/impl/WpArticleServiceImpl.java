package com.windypath.blog.modules.admin.datacontrol.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windypath.blog.common.exception.AddOPException;
import com.windypath.blog.common.exception.DeleteOPException;
import com.windypath.blog.common.exception.UpdateOPException;
import com.windypath.blog.common.model.PageModel;
import com.windypath.blog.common.vo.PageVo;
import com.windypath.blog.modules.admin.datacontrol.dao.WpArticleMapper;
import com.windypath.blog.modules.admin.datacontrol.pojo.WpArticle;
import com.windypath.blog.modules.admin.datacontrol.service.WpArticleService;
import com.windypath.blog.modules.admin.datacontrol.vo.WpArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 博客文章
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 20:20
 */
@Service
public class WpArticleServiceImpl implements WpArticleService {
    @Autowired
    private WpArticleMapper wpArticleMapper;

    @Override
    public PageModel<WpArticleVo> findByPage(PageVo pageVo, WpArticleVo wpArticleVo) {
        PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
        List<WpArticleVo> articleVoList = wpArticleMapper.findByPage(wpArticleVo);
        PageInfo<WpArticleVo> pageInfo =  new PageInfo<>(articleVoList);
        return new PageModel<>(articleVoList, pageInfo);
    }

    @Override
    @Transactional(rollbackFor = AddOPException.class)
    public void save(WpArticleVo wpArticleVo) {
        WpArticle wpArticle = new WpArticle();
        BeanUtils.copyProperties(wpArticleVo, wpArticle);
        wpArticle.setCreateTime(new Date());
        wpArticle.setUpdateTime(new Date());
        // TODO 目前只有admin能添加文章
        wpArticle.setUserId(1);
        Integer id = wpArticleMapper.insertSelective(wpArticle);
        // TODO 新增标签和分类
    }

    @Override
    @Transactional(rollbackFor = UpdateOPException.class)
    public void edit(WpArticleVo wpArticleVo) {
        WpArticle wpArticle = new WpArticle();
        BeanUtils.copyProperties(wpArticleVo, wpArticle);
        wpArticle.setCreateTime(new Date());
        wpArticle.setUpdateTime(new Date());
        // TODO 目前只有admin能添加文章
        wpArticle.setUserId(1);
        Integer id = wpArticleMapper.updateByPrimaryKeySelective(wpArticle);
        // TODO 新增标签和分类
    }

    @Override
    public WpArticleVo getById(Integer id) {
        return wpArticleMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = DeleteOPException.class)
    public void delete(Integer id) {
        wpArticleMapper.deleteByPrimaryKey(id);
        // todo 删除与之关联的标签
    }
}
