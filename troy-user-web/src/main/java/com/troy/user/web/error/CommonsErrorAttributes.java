package com.troy.user.web.error;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.user.web.constants.KeyConstants;
import com.troy.user.web.util.ErrorTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;
import java.util.Set;

/**
 * description 自定义应用异常时的响应消息。未被 GlobalExceptionHandler 处理的异常（如未进入 Controller 前的异常），将被转发到 /error,响应的消息内容通过 getErrorAttributes() 获取
 *
 * @author Han
 * @date 2018/9/27 11:35
 */
@Component
public class CommonsErrorAttributes extends DefaultErrorAttributes {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonsErrorAttributes.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * description 返回错误消息
     *
     * @author Han
     * @date 2018/9/27 11:36
     */
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> originalResult = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Object mediaTypes = requestAttributes.getAttribute(KeyConstants.REQUEST_ATTRIBUTES_MEDIA_TYPES, RequestAttributes.SCOPE_REQUEST);
        if (mediaTypes != null && mediaTypes instanceof Set) {
            Set<Object> mediaTypeSet = (Set<Object>) mediaTypes;
            for (Object mediaType : mediaTypeSet) {
                if (!MediaType.class.isInstance(mediaType)) {
                    continue;
                }
                if (MediaType.TEXT_HTML.isCompatibleWith((MediaType) mediaType)) {
                    //如果 MediaType　是 text/html，则返回 spring mvc 标准错误页面内容
                    LOGGER.error("The originalErrorMessage={}", originalResult.toString());
                    return originalResult;
                }
            }
        }
        Throwable throwable = super.getError(requestAttributes);
        Res<ResData> res = ErrorTranslator.translate(throwable);
        try {
            String resOutJson = objectMapper.writeValueAsString(res);
            Map<String, Object> newResult = objectMapper.readValue(resOutJson, new TypeReference<Map<String, Object>>() {
            });
            //屏蔽错误码
            requestAttributes
                    .setAttribute(KeyConstants.REQUEST_ATTRIBUTES_STATUS_CODE, HttpStatus.OK.value(), RequestAttributes.SCOPE_REQUEST);
            LOGGER.error("The originalErrorMessage={},returnErrorMessage={}", originalResult.toString(), resOutJson);
            return newResult;
        } catch (Exception e) {
            LOGGER.error("Response custom message error,originalErrorMessage=" + originalResult.toString() + "," + res, e);
            return originalResult;
        }
    }
}
