package com.troy.user.service.validator;

import com.troy.commons.exception.verification.VerificationException;

/**
 * description: 数据有效性验证器抽象
 *
 * @author Han
 * @date 2018-11-25 15:59
 */
public interface Validator<I> {

    /**
     * 校验入参
     *
     * @param in
     * @throws VerificationException
     */
    void verify(I in) throws VerificationException;
}
