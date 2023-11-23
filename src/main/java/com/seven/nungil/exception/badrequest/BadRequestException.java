package com.seven.nungil.exception.badrequest;

import com.seven.nungil.exception.AbstractApiException;
import com.seven.nungil.exception.ErrorCode;

public class BadRequestException extends AbstractApiException {
    public BadRequestException(String msg) {
        super(ErrorCode.BAD_REQUEST, msg);
    }
}
