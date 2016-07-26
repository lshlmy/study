package com.lshlmy.study.domain.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by lshlm on 2016/7/19.
 */
public class CommaSetTypeHander extends BaseTypeHandler<Set<String>> {

    private final static String COMMA = ",";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, StringUtils.join(parameter.toArray(), COMMA));
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        Set<String> result;
        if (StringUtils.isNotEmpty(str)) {
            String[] idArray = str.split(COMMA);
            result = new HashSet<>();
            Collections.addAll(result, idArray);
        } else {
            result = Collections.emptySet();
        }
        return result;
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}