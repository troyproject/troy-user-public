package com.troy.user.service.converter;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description 类型转换器模板
 *
 * @author Han
 * @date 2018/9/28 11:31
 */
public abstract class AbstractTypeConverter<I, O> {

    /**
     * 类型缓存
     * key:class name
     * value:动态参数实际类型
     */
    private static final Map<String, Type[]> CACHE_ACTUAL_TYPE_ARGUMENTS_MAP = new HashMap<>();

    /**
     * description: 类型转换
     *
     * @param in 转换前类型
     * @return 转换后类型
     * @throws Exception 转换异常
     */
    public O convert(I in) throws Exception {
        if (in == null) {
            return null;
        }
        Class<O> outType = (Class<O>) this.getActualTypeArguments(this)[1];
        O outObject = outType.newInstance();
        BeanUtils.copyProperties(in, outObject);
        return outObject;
    }

    /**
     * description: 类型转换
     *
     * @param inList 转换前类型
     * @return 转换后类型
     * @throws Exception 转换异常
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
     * description: 获取对象所在类的所有动态参数实际类型
     *
     * @param object 类实例
     * @return 类型
     */
    private Type[] getActualTypeArguments(Object object) {
        if (object == null) {
            return null;
        }
        String className = object.getClass().getName();
        if (CACHE_ACTUAL_TYPE_ARGUMENTS_MAP.containsKey(className)) {
            return CACHE_ACTUAL_TYPE_ARGUMENTS_MAP.get(className);
        }
        ParameterizedType parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        CACHE_ACTUAL_TYPE_ARGUMENTS_MAP.put(className, actualTypeArguments);
        return actualTypeArguments;
    }
}
