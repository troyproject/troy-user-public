package com.troy.user.dto.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * description 类型转换器模板
 *
 * @author Han
 * @date 2018/10/12 11:55
 */
public abstract class AbstractTypeConverter<I, O> {

    /**
     * description 类型批量转换
     *
     * @param inList
     * @return
     * @throws Exception
     * @author Han
     * @date 2018/10/12 11:55
     */
    public List<O> convert(List<I> inList) throws Exception {
        if (inList == null) {
            return null;
        }
        List<O> out = new ArrayList<>();
        for (I in : inList) {
            out.add(this.convert(in));
        }
        return out;
    }

    /**
     * description 类型转换
     *
     * @param in
     * @return
     * @throws Exception
     * @author Han
     * @date 2018/10/12 11:55
     */
    public abstract O convert(I in) throws Exception;
}
