package com.lshlmy.study.common.el;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;
import javax.servlet.jsp.JspContext;

import org.springframework.web.util.HtmlUtils;

/**
 * EL特殊字符转义解析器
 * 
 * @author lshlmy
 */
public class EscapeXmlELResolver extends ELResolver {

	public static final String ESCAPE_XML_ATTRIBUTE = EscapeXmlELResolver.class.getName() + ".escapeXml";

	private ThreadLocal<Boolean> excludeMe = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return Boolean.FALSE;
		}
	};

	@Override
	public Object getValue(ELContext context, Object base, Object property)
			throws NullPointerException, PropertyNotFoundException, ELException {
		JspContext pageContext = (JspContext) context.getContext(JspContext.class);
		Boolean escapeXml = (Boolean) pageContext.getAttribute(ESCAPE_XML_ATTRIBUTE);
		if (escapeXml != null && !escapeXml) {
			return null;
		}
		try {
			if (excludeMe.get()) {
				return null;
			}
			excludeMe.set(Boolean.TRUE);
			Object value = context.getELResolver().getValue(context, base, property);
			if (value instanceof String) {
				value = HtmlUtils.htmlEscape((String) value);
				try {
					value = ((String) value).replaceAll("\n", "&#10;").replaceAll("\r", "&#13;");
				} catch (Exception e) {
				}
			}
			return value;
		} finally {
			excludeMe.remove();
		}
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property)
			throws NullPointerException, PropertyNotFoundException, ELException {
		return null;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object value)
			throws NullPointerException, PropertyNotFoundException, PropertyNotWritableException, ELException {
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property)
			throws NullPointerException, PropertyNotFoundException, ELException {
		return true;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base) {
		return null;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return null;
	}

}