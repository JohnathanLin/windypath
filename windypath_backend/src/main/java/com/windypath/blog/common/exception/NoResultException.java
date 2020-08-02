package com.windypath.blog.common.exception;


import com.windypath.blog.common.response.ResponseCode;
import com.windypath.blog.config.exception.GlobalException;
import lombok.Data;

/**
 * NoResultException
 * 查询结果不存在异常
 *
 * @author Johnathan Lin, E-mail: johnathan@windypath.com
 * @date 2020/8/2 21:19
 */
@Data
public class NoResultException extends GlobalException {

    private ResponseCode responseCode = ResponseCode.ERROR_GET_NO_RESULT;

    public NoResultException(String message) {
        super(message);
    }

}
