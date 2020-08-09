package com.windypath.blog.modules.admin.datacontrol.controller;

import com.windypath.blog.common.response.ResponseData;
import com.windypath.blog.common.vo.PageVo;
import com.windypath.blog.modules.admin.datacontrol.pojo.WpArticle;
import com.windypath.blog.modules.admin.datacontrol.service.WpArticleService;
import com.windypath.blog.modules.admin.datacontrol.vo.WpArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/8 19:46
 */
@Api(value = "文章管理")
@RestController
@RequestMapping("/admin/article")
public class WpArticleController {

    @Autowired
    private WpArticleService wpArticleService;

    @ApiOperation(value = "后台管理分页查询文章")
    @PostMapping("/findByPage")
    public ResponseData findByPage(PageVo pageVo, WpArticleVo wpArticleVo) {
        return ResponseData.success(wpArticleService.findByPage(pageVo, wpArticleVo));
    }

    @ApiOperation(value = "保存文章")
    @PostMapping("/save")
    public ResponseData save(WpArticleVo wpArticleVo) {
        wpArticleService.save(wpArticleVo);
        return ResponseData.successMessage("新增文章成功");
    }

    @ApiOperation(value = "编辑文章")
    @PostMapping("/edit")
    public ResponseData edit(WpArticleVo wpArticleVo) {
        wpArticleService.edit(wpArticleVo);
        return ResponseData.successMessage("编辑文章成功");
    }

    @ApiOperation(value = "根据ID查询文章")
    @PostMapping("/getById")
    public ResponseData getById(@RequestParam("id")Integer id) {
        return ResponseData.success(wpArticleService.getById(id));
    }

    @ApiOperation(value = "删除文章")
    @PostMapping("/delete")
    public ResponseData delete(@RequestParam("id") Integer id) {
        wpArticleService.delete(id);
        return ResponseData.successMessage("删除文章成功");
    }
}
