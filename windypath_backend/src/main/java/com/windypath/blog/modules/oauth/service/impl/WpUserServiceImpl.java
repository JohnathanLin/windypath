package com.windypath.blog.modules.oauth.service.impl;

import com.windypath.blog.modules.oauth.dao.WpUserMapper;
import com.windypath.blog.modules.oauth.pojo.WpUser;
import com.windypath.blog.modules.oauth.service.WpUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/5 22:29
 */
@Service
public class WpUserServiceImpl implements WpUserService {

    @Autowired
    private WpUserMapper wpUserMapper;

    @Override
    public List<WpUser> findByUsername(String username) {
        return wpUserMapper.findByUsername(username);

    }
}
