package com.windypath.blog.common.controller;


import com.alibaba.fastjson.JSON;
import com.windypath.blog.common.response.ResponseData;
import com.windypath.blog.common.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 20:22
 */
@Controller
@RequestMapping("/file")
@Api(value = "文件上传下载", description = "文件上传下载")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return ResponseData.success(fileService.save(file.getInputStream(), file.getOriginalFilename()));
    }

    @ApiOperation(value = "文件下载", notes = "文件下载")
    @RequestMapping(value = "/{fileId}")
    public void download(@PathVariable("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) {
        fileService.findById(fileId, request, response);
    }

    @ApiOperation(value = "文件删除", notes = "文件删除")
    @RequestMapping(value = "/delFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData delFile(String fileId) {
        List<String> listId = (List<String>) JSON.parse(fileId);
        fileService.delFile(listId);
        return ResponseData.successMessage("删除成功");
    }
}
