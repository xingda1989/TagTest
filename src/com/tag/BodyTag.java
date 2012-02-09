package com.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BodyTag extends BodyTagSupport{

	 private int count;  
	 
	 private HttpServletRequest request;  
	 
	 private JspWriter out;  
	 
	 public void init(){
		 request = (HttpServletRequest) pageContext.getRequest();
		 out = pageContext.getOut();
	 }

	@Override
	public int doStartTag() throws JspException {
		init();
		return this.EVAL_BODY_INCLUDE;
	}

	@Override
	public void setBodyContent(BodyContent b) {
		this.bodyContent = b;
		System.out.println("setBodyContent");
	}

	@Override
	public void doInitBody() throws JspException {
		System.out.println("initbody......");
	}

	@Override
	public int doAfterBody() throws JspException {
		if(count >= 1){
			try {
				out.println(count);
				out.print("<br>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count--;
			return this.EVAL_BODY_AGAIN;
		}else{
			return this.SKIP_BODY;
		}
	}
	
	@Override  
	 public int doEndTag() throws JspException {  
	  java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(  
	    "yyyy-MM-dd");  
	  String date = formater.format(new Date());  
	  try {  
	   out.print(date);  
	  } catch (IOException e) {  
	   e.printStackTrace();  
	  }  
	  return this.EVAL_PAGE;  
	 }  
	 
	 // 必须实现setXX()方法  
	 public void setCount(int count) {  
	  this.count = count;  
	 }  

}
