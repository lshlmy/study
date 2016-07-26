package com.lshlmy.study.domain.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lshlmy.study.common.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;


/**
 * @author lshlmy
 * @description 用于mybatis处理JSON数据
 */
public class JsonTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtil.stringify(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return JsonUtil.parse(rs.getString(columnName), Object.class);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JsonUtil.parse(rs.getString(columnIndex), Object.class);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return JsonUtil.parse(cs.getString(columnIndex), Object.class);
    }

}
