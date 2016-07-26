package com.lshlmy.study.common.http;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.lshlmy.study.common.config.security.CustomAuthenticationToken;
import com.lshlmy.study.common.util.DateTimeUtils;
import com.lshlmy.study.domain.entity.User;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;



/**
 * HttpServletRequest包装类
 *
 * @author 杨海彬
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 构造方法
     *
     * @param request HttpServletRequest
     */
    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 获取参数值
     */
    private String getValue(String name, boolean notEmpty, boolean trim) {
        String value = trim ? StringUtils.trim(super.getParameter(name)) : super.getParameter(name);
        if (notEmpty && StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("请求参数【" + name + "】不能为空");
        }
        return value;
    }

    /**
     * 获取Boolean值，不允许返回空值
     */
    public Boolean getBoolean(String name) {
        return getBoolean(name, true);
    }

    /**
     * 获取Boolean值，允许返回空值
     */
    public Boolean getBooleanQuietly(String name) {
        return getBoolean(name, false);
    }

    /**
     * 获取Boolean值
     */
    private Boolean getBoolean(String name, boolean notEmpty) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Boolean.valueOf(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取Integer值，不允许返回空值
     */
    public Integer getInteger(String name) {
        return getInteger(name, true, null, null);
    }

    /**
     * 获取Integer值，并限制最小值和最大值，不允许返回空值
     */
    public Integer getInteger(String name, Integer min, Integer max) {
        return getInteger(name, true, min, max);
    }

    /**
     * 获取Integer值，允许返回空值
     */
    public Integer getIntegerQuietly(String name) {
        return getInteger(name, false, null, null);
    }

    /**
     * 获取Integer值，并限制最小值和最大值，允许返回空值
     */
    public Integer getIntegerQuietly(String name, Integer min, Integer max) {
        return getInteger(name, false, min, max);
    }

    /**
     * 获取Integer值
     */
    private Integer getInteger(String name, boolean notEmpty, Integer min, Integer max) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            Integer result = Integer.valueOf(value);
            if (min != null && result < min) {
                throw new Exception();
            } else if (max != null && result > max) {
                throw new Exception();
            }
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取Long值，不允许返回空值
     */
    public Long getLong(String name) {
        return getLong(name, true, null, null);
    }

    /**
     * 获取Long值，并限制最小值和最大值，不允许返回空值
     */
    public Long getLong(String name, Long min, Long max) {
        return getLong(name, true, min, max);
    }

    /**
     * 获取Long值，允许返回空值
     */
    public Long getLongQuietly(String name) {
        return getLong(name, false, null, null);
    }

    /**
     * 获取Long值，并限制最小值和最大值，允许返回空值
     */
    public Long getLongQuietly(String name, Long min, Long max) {
        return getLong(name, false, min, max);
    }

    /**
     * 获取Long值
     */
    private Long getLong(String name, boolean notEmpty, Long min, Long max) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            Long result = Long.valueOf(value);
            if (min != null && result < min) {
                throw new Exception();
            } else if (max != null && result > max) {
                throw new Exception();
            }
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取BigDecimal值，不允许返回空值
     */
    public BigDecimal getBigDecimal(String name) {
        return getBigDecimal(name, true, null, null, null, null);
    }

    /**
     * 获取BigDecimal值，并限制最小值和最大值，不允许返回空值
     */
    public BigDecimal getBigDecimal(String name, Number min, Number max) {
        return getBigDecimal(name, true, min, max, null, null);
    }

    /**
     * 获取BigDecimal值，并限制最小值、最大值、最大长度和最大精度，不允许返回空值
     */
    public BigDecimal getBigDecimal(String name, Number min, Number max, Integer maxPrecision, Integer maxScale) {
        return getBigDecimal(name, true, min, max, maxPrecision, maxScale);
    }

    /**
     * 获取BigDecimal值，允许返回空值
     */
    public BigDecimal getBigDecimalQuietly(String name) {
        return getBigDecimal(name, false, null, null, null, null);
    }

    /**
     * 获取BigDecimal值，并限制最小值和最大值，允许返回空值
     */
    public BigDecimal getBigDecimalQuietly(String name, Number min, Number max) {
        return getBigDecimal(name, false, min, max, null, null);
    }

    /**
     * 获取BigDecimal值，并限制最小值、最大值、最大长度和最大精度，允许返回空值
     */
    public BigDecimal getBigDecimalQuietly(String name, Number min, Number max, Integer maxPrecision, Integer maxScale) {
        return getBigDecimal(name, false, min, max, maxPrecision, maxScale);
    }

    /**
     * 获取BigDecimal值
     */
    private BigDecimal getBigDecimal(String name, boolean notEmpty, Number min, Number max, Integer maxPrecision, Integer maxScale) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            BigDecimal result = new BigDecimal(value);
            if (min != null && result.doubleValue() < min.doubleValue()) {
                throw new Exception();
            } else if (max != null && result.doubleValue() > max.doubleValue()) {
                throw new Exception();
            } else if (maxPrecision != null && result.precision() > maxPrecision) {
                throw new Exception();
            } else if (maxScale != null && result.scale() > maxScale) {
                throw new Exception();
            }
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取String值，不允许返回空值
     */
    public String getString(String name) {
        return getString(name, true, false, null, null, null);
    }

    /**
     * 获取String值，并匹配正则表达式，不允许返回空值
     */
    public String getString(String name, String regex) {
        return getString(name, true, false, regex, null, null);
    }

    /**
     * 获取String值，并限制最小长度和最大长度，不允许返回空值
     */
    public String getString(String name, Integer minLength, Integer maxLength) {
        return getString(name, true, false, null, minLength, maxLength);
    }

    /**
     * 获取String值，并匹配正则表达式、限制最小值和最大值，不允许返回空值
     */
    public String getString(String name, String regex, Integer minLength, Integer maxLength) {
        return getString(name, true, false, regex, minLength, maxLength);
    }

    /**
     * 获取String值，允许返回空值
     */
    public String getStringQuietly(String name) {
        return getString(name, false, false, null, null, null);
    }

    /**
     * 获取String值，并匹配正则表达式，允许返回空值
     */
    public String getStringQuietly(String name, String regex) {
        return getString(name, false, false, regex, null, null);
    }

    /**
     * 获取String值，并限制最小长度和最大长度，允许返回空值
     */
    public String getStringQuietly(String name, Integer minLength, Integer maxLength) {
        return getString(name, false, false, null, minLength, maxLength);
    }

    /**
     * 获取String值，并匹配正则表达式、限制最小值和最大值，允许返回空值
     */
    public String getStringQuietly(String name, String regex, Integer minLength, Integer maxLength) {
        return getString(name, false, false, regex, minLength, maxLength);
    }

    /**
     * 获取去空格String值，不允许返回空值
     */
    public String getStringTrim(String name) {
        return getString(name, true, true, null, null, null);
    }

    /**
     * 获取去空格String值，并匹配正则表达式，不允许返回空值
     */
    public String getStringTrim(String name, String regex) {
        return getString(name, true, true, regex, null, null);
    }

    /**
     * 获取去空格String值，并限制最小长度和最大长度，不允许返回空值
     */
    public String getStringTrim(String name, Integer minLength, Integer maxLength) {
        return getString(name, true, true, null, minLength, maxLength);
    }

    /**
     * 获取去空格String值，并匹配正则表达式、限制最小值和最大值，不允许返回空值
     */
    public String getStringTrim(String name, String regex, Integer minLength, Integer maxLength) {
        return getString(name, true, true, regex, minLength, maxLength);
    }

    /**
     * 获取去空格String值，允许返回空值
     */
    public String getStringTrimQuietly(String name) {
        return getString(name, false, true, null, null, null);
    }

    /**
     * 获取去空格String值，并匹配正则表达式，允许返回空值
     */
    public String getStringTrimQuietly(String name, String regex) {
        return getString(name, false, true, regex, null, null);
    }

    /**
     * 获取去空格String值，并限制最小长度和最大长度，允许返回空值
     */
    public String getStringTrimQuietly(String name, Integer minLength, Integer maxLength) {
        return getString(name, false, true, null, minLength, maxLength);
    }

    /**
     * 获取去空格String值，并匹配正则表达式、限制最小值和最大值，允许返回空值
     */
    public String getStringTrimQuietly(String name, String regex, Integer minLength, Integer maxLength) {
        return getString(name, false, true, regex, minLength, maxLength);
    }

    /**
     * 获取String值
     */
    private String getString(String name, boolean notEmpty, boolean trim, String regex, Integer minLength, Integer maxLength) {
        String value = getValue(name, notEmpty, trim);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            if (minLength != null && value.length() < minLength) {
                throw new Exception();
            } else if (maxLength != null && value.length() > maxLength) {
                throw new Exception();
            } else if (StringUtils.isNotEmpty(regex) && !value.matches(regex)) {
                throw new Exception();
            }
            return value;
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取String数组值，不允许返回空值
     */
    public String[] getStringArray(String name) {
        return getStringArray(name, true);
    }

    /**
     * 获取String数组值，允许返回空值
     */
    public String[] getStringArrayQuietly(String name) {
        return getStringArray(name, false);
    }

    /**
     * 获取String数组值
     */
    private String[] getStringArray(String name, boolean notEmpty) {
        String[] result = super.getParameterValues(name);
        if (notEmpty && ArrayUtils.isEmpty(result)) {
            throw new IllegalArgumentException("请求参数【" + name + "】不能为空");
        }
        return result;
    }

    /**
     * 获取Date值，不允许返回空值
     */
    public Date getDate(String name, String pattern) {
        return getDate(name, pattern, true, false);
    }

    /**
     * 获取Date值，并将Time值设为最大，不允许返回空值
     */
    public Date getDate(String name, String pattern, boolean toMaxTime) {
        return getDate(name, pattern, true, toMaxTime);
    }

    /**
     * 获取Date值，允许返回空值
     */
    public Date getDateQuietly(String name, String pattern) {
        return getDate(name, pattern, false, false);
    }

    /**
     * 获取Date值，并将Time值设为最大，允许返回空值
     */
    public Date getDateQuietly(String name, String pattern, boolean toMaxTime) {
        return getDate(name, pattern, false, toMaxTime);
    }

    /**
     * 获取Date值
     */
    private Date getDate(String name, String pattern, boolean notEmpty, boolean toMaxTime) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            Date result = new SimpleDateFormat(pattern).parse(value);
            return toMaxTime ? DateTimeUtils.getDayMaxTime(result) : result;
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取Enum值，不允许返回空值
     */
    public <T extends Enum<T>> T getEnum(String name, Class<T> clazz) {
        return getEnum(name, clazz, true);
    }

    /**
     * 获取Enum值，允许返回空值
     */
    public <T extends Enum<T>> T getEnumQuietly(String name, Class<T> clazz) {
        return getEnum(name, clazz, false);
    }

    /**
     * 获取Enum值
     */
    private <T extends Enum<T>> T getEnum(String name, Class<T> clazz, boolean notEmpty) {
        String value = getValue(name, notEmpty, true);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return Enum.valueOf(clazz, value);
        } catch (Exception e) {
            throw new IllegalArgumentException("请求参数【" + name + "】格式不正确");
        }
    }

    /**
     * 获取分页PageNum值
     */
    public int getPageNum() {
        Integer pageNum = getIntegerQuietly("pageNum");
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        return pageNum;
    }

    /**
     * 获取分页PageSize值，默认10条
     */
    public int getPageSize() {
        return getPageSize(10);
    }

    /**
     * 获取分页PageSize值
     */
    public int getPageSize(int defaultValue) {
        Integer pageSize = getIntegerQuietly("pageSize", null, 100);
        if (pageSize == null || pageSize < 1) {
            pageSize = defaultValue;
        }
        return pageSize;
    }

    /**
     * 获取JSONP回调函数名
     */
    public String getJsonpCallback() {
        return getStringQuietly("callback", "^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

    /**
     * 获取请求客户端真实IP
     */
    public String getRequestIp() {
        String ip = super.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = super.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = super.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = super.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 是否为AJAX请求
     */
    public boolean isAjaxRequest() {
        return "XMLHttpRequest".equalsIgnoreCase(super.getHeader("X-Requested-With"));
    }

    /**
     * 获取当前登录的用户
     */
    public User getUser() {
        CustomAuthenticationToken token = (CustomAuthenticationToken) super.getUserPrincipal();
        return token == null ? null : (User) token.getPrincipal();
    }

    /**
     * 获取ZsToken
     */
    public String getToken() {
        CustomAuthenticationToken token = (CustomAuthenticationToken) super.getUserPrincipal();
        return token == null ? null : token.getZsToken();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(super.getParameterMap());
    }
}