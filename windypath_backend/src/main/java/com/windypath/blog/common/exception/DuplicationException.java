package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * DuplicationException
 * 数据重复异常
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class DuplicationException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_DATA_DUPLICATION;

    public DuplicationException(String message) {
        super(message);
    }
}
