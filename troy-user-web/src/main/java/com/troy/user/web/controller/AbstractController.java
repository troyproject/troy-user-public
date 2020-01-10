package com.troy.user.web.controller;

import com.troy.commons.BaseController;
import com.troy.commons.exception.configuration.ConfigurationException;
import com.troy.user.client.auth.TokenConverter;
import com.troy.user.dto.out.auth.token.CheckTokenResData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base Controller
 *
 * @author Han
 */
public abstract class AbstractController extends BaseController {

    @Autowired
    protected TokenConverter tokenConverter;

    protected CheckTokenResData currentUser() throws Exception {
        if (this.tokenConverter == null) {
            throw new ConfigurationException("TokenConverter is null,Check the publicKey configuration of the TokenConverter");
        }
        CheckTokenResData checkTokenResData = this.tokenConverter.convertAccessToken(this.currentRequest());
        return checkTokenResData;
    }
}
