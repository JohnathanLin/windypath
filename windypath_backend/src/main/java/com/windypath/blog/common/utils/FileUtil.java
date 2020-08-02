package com.windypath.blog.common.utils;

import com.windypath.blog.common.consts.CommonConst;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件工具类
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
public class FileUtil {

    /**
     * 解决下载文件名中文乱码
     *
     * @param request  HttpServletRequest
     * @param fileName 文件名
     * @return 处理后的文件名
     */
    public static String handleFileName(HttpServletRequest request, String fileName) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        boolean isIE = userAgent.contains("MSIE") || (userAgent.contains("GECKO") && userAgent.contains("RV:11"));
        try {
            if (isIE) {
                fileName = URLEncoder.encode(fileName, CommonConst.UTF_8);
            } else {
                fileName = new String(fileName.getBytes(CommonConst.UTF_8), CommonConst.ISO8859_1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}