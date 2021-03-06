package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * DeleteOPException
 * 删除操作异常
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class DeleteOPException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_DELETE_OP;

    public DeleteOPException(String message) {
        super(message);
    }

}
