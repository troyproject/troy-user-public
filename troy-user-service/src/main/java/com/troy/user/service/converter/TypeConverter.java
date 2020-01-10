package com.troy.user.service.converter;

import java.util.List;

/**
 * description 类型转换器抽象
 *
 * @author Han
 * @date 2018/9/28 11:18
 */
public interface TypeConverter<I, O> {

    /**
     * description: 类型转换
     *
     * @param in 转换前类型
     * @return 转换后类型
     * @throws Exception 转换异常
     */
    O convert(I in) throws Exception;

    /**
     * description: 类型转换
     *
     * @param in 转换前类型
     * @return 转换后类型
     * @throws Exception 转换异常
     */
    List<O> convert(List<I> in) throws Exception;
}
