package com.troy.user.dto.in.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhangchengjie
 * @date 2019/08/01
 */
@Setter
@Getter
@Builder
public class BindCodeCacheDto {

    /**
     * 绑定类型 1邮箱 2手机 3谷歌验证码
     */
    private Integer bindType;

    /**
     * 验证方式 1邮箱 2手机 3谷歌
     */
    private List<Integer> verifyWays;

    /**
     * 发送方式 1邮箱 2手机 3谷歌
     */
    private Integer sendWay;

    /**
     * 操作类型 1绑定 2解绑
     */
    private Integer operateType;

    private Long userId;

}
