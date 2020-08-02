package com.windypath.blog.common.exception;

import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;

/**
 * ModifyPasswordException
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
public class ModifyPasswordException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_MODIFY_PASSWORD;

    public ModifyPasswordException(String message) {
        super(message);
    }

}
