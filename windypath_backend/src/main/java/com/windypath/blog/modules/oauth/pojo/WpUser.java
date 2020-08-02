package com.windypath.blog.modules.oauth.pojo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator 2020/08/02
*/
@Getter
@Setter
@ToString
@Builder
public class WpUser {
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String email;

    private String mobile;

    private String signature;

    private Date createTime;

    private Date updateTime;

    private Integer disabled;
}