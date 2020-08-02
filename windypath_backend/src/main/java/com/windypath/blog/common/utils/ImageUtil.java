package com.windypath.blog.common.utils;

import java.util.regex.Pattern;

/**
 * 图片工具类
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
public class ImageUtil {

    /**
     * 图片格式校验正则
     */
    private static final Pattern IMAGE_PATTERN = Pattern.compile(".+(\\.bmp|\\.jpg|\\.png|\\.tif|\\.gif|\\.pcx|\\.exif|\\.fpx|\\.svg|\\.psd|\\.cdr|\\.pcd|\\.dxf|\\.ufo|\\.eps|\\.ai|\\.raw|\\.wmf|\\.webp)$", Pattern.CASE_INSENSITIVE);

    /**
     * 校验文件是否为图片格式
     *
     * @param fileName 文件名
     * @return 是：true 否：false
     */
    public static boolean isImage(String fileName) {
        return IMAGE_PATTERN.matcher(fileName).matches();
    }

}