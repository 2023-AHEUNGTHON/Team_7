package com.seven.nungil.exception.unauthorized;

import com.seven.nungil.exception.AbstractApiException;
import com.seven.nungil.exception.ErrorCode;
public class UnauthorizedException extends AbstractApiException {
    public UnauthorizedException(String msg) {
        super(ErrorCode.UNAUTHORIZED, msg);
    }
}
