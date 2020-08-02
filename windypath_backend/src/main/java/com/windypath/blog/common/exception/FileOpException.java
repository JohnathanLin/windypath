package com.windypath.blog.common.exception;

import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * 文件操作异常
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class FileOpException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_FILE_OP;

    public FileOpException(String message) {
        super(message);
    }
}
