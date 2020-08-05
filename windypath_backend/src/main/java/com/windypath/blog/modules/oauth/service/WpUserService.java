package com.windypath.blog.modules.oauth.service;

import com.windypath.blog.modules.oauth.pojo.WpUser;

import java.util.List;

/**
 * 用户
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/5 22:29
 */
public interface WpUserService {
    /**
     * 查询用户名相同的用户
     *
     * @param username 用户名
     * @return
     */
    List<WpUser> findByUsername(String username);
}
