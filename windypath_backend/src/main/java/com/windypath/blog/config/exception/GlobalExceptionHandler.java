package com.windypath.blog.config.exception;


import com.windypath.blog.common.exception.*;
import com.windypath.blog.common.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobalExceptionHandler
 * 全局异常处理器
 * 当请求处理出现异常时，会根据异常处理器的配置顺序，依次尝试异常匹配和处理
 * 自定义异常处理器需要继承GlobalException类
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 自定义的异常处理器

    @ExceptionHandler(value = FileOpException.class)
    @ResponseBody
    public ResponseData fileOpExceptionHandler(HttpServletRequest request, FileOpException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    @ExceptionHandler(value = DeleteOPException.class)
    @ResponseBody
    public ResponseData deleteExceptionHandler(HttpServletRequest request, DeleteOPException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    @ExceptionHandler(value = NoResultException.class)
    @ResponseBody
    public ResponseData noResultExceptionHandler(HttpServletRequest request, NoResultException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    @ExceptionHandler(value = DuplicationException.class)
    @ResponseBody
    public ResponseData duplicationExeptionHandler(HttpServletRequest request, DuplicationException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ParamException.class)
    @ResponseBody
    public ResponseData paramExeptionHandler(HttpServletRequest request, ParamException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    /**
     * 全局异常处理器
     *
     * @param request HttpServletRequest
     * @param e       Exception
     * @return ResponseData
     * @Exception
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public ResponseData globalExceptionHandler(HttpServletRequest request, GlobalException e) {
        return ResponseData.error(e.getResponseCode().getCode(), e.getMessage());
    }

    /**
     * 默认异常处理器
     *
     * @param request HttpServletRequest
     * @param e       Exception
     * @return ResponseData
     * @Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("【系统异常】", e);
        return ResponseData.errorMessage("系统异常");
    }
}