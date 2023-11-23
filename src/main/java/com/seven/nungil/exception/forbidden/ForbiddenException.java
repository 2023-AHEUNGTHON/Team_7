package com.seven.nungil.exception.forbidden;

import com.seven.nungil.exception.AbstractApiException;
import com.seven.nungil.exception.ErrorCode;

public class ForbiddenException extends AbstractApiException {
    public ForbiddenException(String msg) {
        super(ErrorCode.FORBIDDEN, msg);
    }
}
