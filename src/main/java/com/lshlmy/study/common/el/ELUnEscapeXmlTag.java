package com.lshlmy.study.common.el;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * EL特殊字符不进行转义标签
 * 
 * @author 杨海彬
 */
public class ELUnEscapeXmlTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		pageContext.setAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE, false);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		pageContext.setAttribute(EscapeXmlELResolver.ESCAPE_XML_ATTRIBUTE, true);
		return EVAL_PAGE;
	}
}