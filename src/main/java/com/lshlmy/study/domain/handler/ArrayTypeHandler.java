package com.lshlmy.study.domain.handler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description mybatis 处理对象数组数据
 * @author lshlmy
 */
public class ArrayTypeHandler extends BaseTypeHandler<Object[]> {

	private final static Logger LOGGER = LoggerFactory.getLogger(ArrayTypeHandler.class);

	private static final String TYPE_NAME_VARCHAR = "varchar";
	private static final String TYPE_NAME_INTEGER = "integer";
	private static final String TYPE_NAME_BOOLEAN = "boolean";
	private static final String TYPE_NAME_NUMERIC = "numeric";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
		String typeName = null;
		if (parameter instanceof Integer[]) {
			typeName = TYPE_NAME_INTEGER;
		} else if (parameter instanceof String[]) {
			typeName = TYPE_NAME_VARCHAR;
		} else if (parameter instanceof Boolean[]) {
			typeName = TYPE_NAME_BOOLEAN;
		} else if (parameter instanceof Double[] || parameter instanceof Float[]) {
			typeName = TYPE_NAME_NUMERIC;
		} else {
			throw new TypeException("类型错误，传入的参数类型是" + parameter.getClass().getName());
		}
		Connection conn = ps.getConnection();
		Array array = conn.createArrayOf(typeName, parameter);
		ps.setArray(i, array);
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return getArray(rs.getArray(columnName));
	}

	@Override
	public Object[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getArray(rs.getArray(columnIndex));
	}

	@Override
	public Object[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getArray(cs.getArray(columnIndex));
	}

	private Object[] getArray(Array array) {
		if (array != null) {
			try {
				return (Object[]) array.getArray();
			} catch (Exception e) {
				LOGGER.error("mybatis转换数组数据错误," + e.getMessage(), e);
			}
		}
		return null;
	}
}
