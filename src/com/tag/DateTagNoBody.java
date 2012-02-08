package com.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTagNoBody extends TagSupport{

	private String pattern;
	
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request;
		request = (HttpServletRequest) pageContext.getRequest();
		SimpleDateFormat formater = new SimpleDateFormat(pattern);
		String date = formater.format(new Date());
		JspWriter out = pageContext.getOut();
		try {
			out.print(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	
}
