package com.troy.user.dao.handler;

import com.troy.user.dao.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * description 通用 java type 为集合类型的处理器
 *
 * @author Han
 * @date 2018-10-29 13:11
 */
public class CommonsCollectionTypeHandler extends BaseTypeHandler<Collection<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Collection<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, StringUtils.join(parameter, Constants.DEFAULT_VALUE_SEPARATOR));
    }

    @Override
    public Collection<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.convert(rs.getString(columnName));
    }

    @Override
    public Collection<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.convert(rs.getString(columnIndex));
    }

    @Override
    public Collection<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.convert(cs.getString(columnIndex));
    }

    private Collection<String> convert(String originalValue) throws SQLException {
        if (originalValue == null) {
            return null;
        }
        if (originalValue.isEmpty()) {
            return new HashSet<>(0);
        }
        String[] arrayValue = originalValue.split(Constants.DEFAULT_VALUE_SEPARATOR);
        Set<String> newValue = new HashSet<>(arrayValue.length);
        for (String value : arrayValue) {
            newValue.add(value);
        }
        return newValue;
    }
}
