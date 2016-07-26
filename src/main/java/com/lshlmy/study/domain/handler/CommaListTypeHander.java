package com.lshlmy.study.domain.handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lshlm on 2016/7/19.
 */
public class CommaListTypeHander extends BaseTypeHandler<List<String>> {

    private final static String COMMA = ",";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, StringUtils.join(parameter.toArray(), COMMA));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        List<String> result;
        if (StringUtils.isNotEmpty(str)) {
            result = new ArrayList<>();
            String[] idArray = str.split(COMMA);
            Collections.addAll(result, idArray);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}


