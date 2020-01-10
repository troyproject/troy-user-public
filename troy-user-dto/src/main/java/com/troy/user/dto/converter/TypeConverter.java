package com.troy.user.dto.converter;

import java.util.List;

/**
 * description 类型转换器
 *
 * @author Han
 * @date 2018/10/12 11:55
 */
public interface TypeConverter<I, O> {

    /**
     * description 类型转换
     *
     * @param in
     * @return
     * @throws Exception
     * @author Han
     * @date 2018/10/12 11:55
     */
    O convert(I in) throws Exception;

    /**
     * description 类型批量转换
     *
     * @param in
     * @return
     * @throws Exception
     * @author Han
     * @date 2018/10/12 11:55
     */
    List<O> convert(List<I> in) throws Exception;
}
