package com.windypath.blog.modules.oauth.dao;

import com.windypath.blog.modules.oauth.pojo.WpUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator 2020/08/02
*/
@Mapper
public interface WpUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WpUser entity);

    int insertSelective(WpUser entity);

    WpUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WpUser entity);

    int updateByPrimaryKey(WpUser entity);

    /**
     * 根据用户名查找对应的用户
     *
     * @param username 用户名
     * @return
     */
    List<WpUser> findByUsername(@Param("username") String username);
}