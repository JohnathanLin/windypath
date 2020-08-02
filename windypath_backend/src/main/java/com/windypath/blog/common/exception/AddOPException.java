package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * AddOPException
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class AddOPException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_ADD_OP;

    public AddOPException(String message) {
        super(message);
    }

}
