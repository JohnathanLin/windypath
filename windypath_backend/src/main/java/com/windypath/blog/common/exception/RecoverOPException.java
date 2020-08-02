package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;

/**
 * RecoverOPException
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
public class RecoverOPException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_RECOVER_OP;

    public RecoverOPException(String message) {
        super(message);
    }

}
