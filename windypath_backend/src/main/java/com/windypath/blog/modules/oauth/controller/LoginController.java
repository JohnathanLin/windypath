package com.windypath.blog.modules.oauth.controller;

import com.windypath.blog.common.response.ResponseData;
import com.windypath.blog.modules.oauth.pojo.WpUser;
import com.windypath.blog.modules.oauth.service.WpUserService;
import com.windypath.blog.modules.oauth.vo.WpUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Api(value = "登录、退出、忘记密码")
@RequestMapping("/oauth")
@RestController
public class LoginController {
    @Autowired
    private WpUserService wpUserService;

    @ApiOperation(value = "系统管理员登录", notes = "系统管理员登录")
    @PostMapping("/login")
    public ResponseData login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) {

        List<WpUser> wpUserList = wpUserService.findByUsername(username);

        if (wpUserList.size() <= 0) {
            return ResponseData.errorMessage("用户名不能为空");
        } else if (StringUtils.isBlank(password)) {
            return ResponseData.errorMessage("登陆密码不能为空");
        }
        if (wpUserList.size() > 1) {
            return ResponseData.errorMessage("您的用户信息有误，请联系管理员");
        }

        WpUser wpUser = wpUserList.get(0);
        if (wpUser == null) {
            return ResponseData.errorMessage("查询不到指定用户");
        } else if (!wpUser.getPassword().equals(password)) {
            return ResponseData.errorMessage("用户名或密码错误");
        } else if (wpUser.getDisabled() == 1) {
            return ResponseData.errorMessage("用户已被冻结，请联系管理员");
        } else {    // LOGIN SUCCESS
            WpUserVo wpUserVo = new WpUserVo();
            BeanUtils.copyProperties(wpUser, wpUserVo);
            session.setAttribute("user", wpUserVo);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", session.getId());
        tokenMap.put("userId", wpUser.getId().toString());
        return ResponseData.success(tokenMap);

    }

    @ApiOperation(value = "用户登出接口", notes = "登出操作清空session信息")
    @RequestMapping("/logout")
    public ResponseData logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return ResponseData.successMessage("登出成功");
    }
}
