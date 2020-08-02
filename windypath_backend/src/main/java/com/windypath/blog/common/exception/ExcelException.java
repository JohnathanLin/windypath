package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * Excel异常
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class ExcelException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.EXCEL_ERROR;

    public ExcelException(String message) {
        super(message);
    }
}
