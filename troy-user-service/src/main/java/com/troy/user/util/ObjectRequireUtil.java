package com.troy.user.util;

import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * ObjectRequireUtil
 *
 * @author  zhangchengjie
 * @date  2019/07/30
 */
public class ObjectRequireUtil {

    public static void requireNull(Object obj, StateTypeSuper stateType) {
        if (obj != null) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonNull(Object obj, StateTypeSuper stateType) {
        if (obj == null) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEmpty(String obj, StateTypeSuper stateType) {
        if (StringUtils.isEmpty(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEmpty(Collection obj, StateTypeSuper stateType) {
        if (CollectionUtils.isEmpty(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEmpty(Map obj, StateTypeSuper stateType) {
        if (CollectionUtils.isEmpty(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEmpty(List obj, StateTypeSuper stateType) {
        if (CollectionUtils.isEmpty(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEmpty(Set obj, StateTypeSuper stateType) {
        if (CollectionUtils.isEmpty(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireTrue(Boolean obj, StateTypeSuper stateType) {
        requireNonNull(obj, stateType);
        if (!obj.booleanValue()) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireFalse(Boolean obj, StateTypeSuper stateType) {
        requireNonNull(obj, stateType);
        if (obj.booleanValue()) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireTrue(boolean obj, StateTypeSuper stateType) {
        if (!obj) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireFalse(boolean obj, StateTypeSuper stateType) {
        if (obj) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEquals(Object expect, Object actual, StateTypeSuper stateType) {
        requireNonNull(expect, stateType);
        requireNonNull(actual, stateType);
        if (expect.equals(actual)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEquals(Integer expect, Integer actual, StateTypeSuper stateType) {
        requireNonNull(expect, stateType);
        requireNonNull(actual, stateType);
        if (expect.equals(actual)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonEquals(Long expect, Long actual, StateTypeSuper stateType) {
        requireNonNull(expect, stateType);
        requireNonNull(actual, stateType);
        if (expect.equals(actual)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireEquals(Object expect, Object actual, StateTypeSuper stateType) {
        requireNonNull(expect, stateType);
        requireNonNull(actual, stateType);
        if (!expect.equals(actual)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireContains(Collection collection, Object obj, StateTypeSuper stateType) {
        requireNonNull(collection, stateType);
        requireNonNull(obj, stateType);

        if (!collection.contains(obj)) {
            throw new ServiceException(stateType);
        }
    }

    public static void requireGreaterThan(Comparable a,Comparable b, StateTypeSuper stateType) {
        requireNonNull(a, stateType);
        requireNonNull(b, stateType);
        if( a.compareTo(b) <= 0 ){
            throw new ServiceException(stateType);
        }
    }

    public static void requireGreaterThanOrEquals(Comparable a,Comparable b, StateTypeSuper stateType) {
        requireNonNull(a, stateType);
        requireNonNull(b, stateType);
        if( a.compareTo(b) < 0 ){
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonZero(Integer num, StateTypeSuper stateType){
        requireNonNull(num, stateType);
        if( num.intValue() == 0 ){
            throw new ServiceException(stateType);
        }
    }

    public static void requireZero(Integer num, StateTypeSuper stateType){
        requireNonNull(num, stateType);
        if( num.intValue() != 0 ){
            throw new ServiceException(stateType);
        }
    }

    public static void requireNonZero(BigDecimal num, StateTypeSuper stateType){
        requireNonNull(num, stateType);
        if( num.setScale(0, RoundingMode.HALF_EVEN).intValue() == 0 ){
            throw new ServiceException(stateType);
        }
    }

}
