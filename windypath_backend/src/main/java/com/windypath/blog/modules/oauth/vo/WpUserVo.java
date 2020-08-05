package com.windypath.blog.modules.oauth.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/5 22:43
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WpUserVo implements Serializable {
  
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 签名
     */
    private String signature;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 编辑时间
     */
    private Date updateTime;
    /**
     * 是否被禁用 （0：可用，1禁用）
     */
    private Integer disabled;
}
