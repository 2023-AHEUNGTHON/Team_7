package com.seven.nungil.exception.notfound;

import com.seven.nungil.exception.AbstractApiException;
import com.seven.nungil.exception.ErrorCode;
public class NotFoundException extends AbstractApiException {
    public NotFoundException(String msg) {
        super(ErrorCode.RESOURCE_NOT_FOUND, msg);
    }
}
