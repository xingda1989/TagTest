package com.tag;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Pagination extends TagSupport{

	
	private int curr;  
	private int total;
	private int start;
	private int end;
	 
	private HttpServletRequest request;  
	 
	private JspWriter out;
	
	public void init(){
		request = (HttpServletRequest) pageContext.getRequest();
		out = pageContext.getOut();
	}
	
	@Override
	public int doStartTag() throws JspException {
		init();
		String forwardPage = request.getParameter("page");
		if(forwardPage != null){
			curr = Integer.valueOf(forwardPage);
		}
		if(curr > total){
			curr = 1;
		}
		String head = getHead(curr,total);
		String nav = getNav(curr,start,end);
		String content = MessageFormat.format("<div class='pagination'>{0}{1}</div>",head,nav);
		try {
			out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	
	 public int doEndTag() throws JspException {  
		  return this.EVAL_PAGE;  
		 }  

	private String getHead(int curr,int total){
		return MessageFormat.format("<span class=\"pagecount\"><span>第{0}页&nbsp;&nbsp;</span><span>共{1}页&nbsp;&nbsp;</span></span>", curr,total);
	}
	
	private String getNav(int curr,int start,int end){
		StringBuilder nav = new StringBuilder("<span class=\"pagenav\">");
		String body = "";
		if(curr > start){
			nav.append("<button class=\"pageup\" >上一页</button>&nbsp;");
		}
		for(int i=start;i<=end;i++){
			if(i != curr){
				body = MessageFormat.format("<span class=\"pagenumber\" ><a href=\"?page={1}\" >{0}</a>&nbsp;</span>",i,i);
			}else{
				body = MessageFormat.format("<span class=\"currPage\" >{0}&nbsp;</span>",i );
			}
			nav.append(body);
		}
		if(curr < end){
			nav.append("<button class=\"nextpage\">下一页</button>");
		}
		nav.append("</span>");
		return nav.toString();
	}
	
	public void setCurr(int curr) {
		this.curr = curr;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
/*
  
  
 <div class="pagination">
		<span class="pagecount"><span>第$paginator.getPage()页&nbsp;&nbsp;</span><span>共$paginator.getPages()页&nbsp;&nbsp;</span></span>
		#if($paginator.getPages()>1)
			<span class="pagenav">
				#if($paginator.getPage()>1)
					<button class="pageup" onclick="goPage($paginator.getPreviousPage());">上一页</button>
				#end
				#set($slide=$paginator.getSlider(6))
				#foreach($slideItem in $slide)
					#if($paginator.getPage() == ${slideItem})
						<span class="pagenumber1">${slideItem}&nbsp;</span>
						<!--当前页面-->
					#else
						<span class="pagenumber"><a href="javascript:goPage(${slideItem});" >${slideItem}</a>&nbsp;</span>
						<!--非当前页面-->
					#end
				#end
				#if($paginator.getPage() != $paginator.getPages())
					<button class="nextpage" onclick="goPage($paginator.getNextPage());">下一页</button>
				#end
			</span>
		#end
	</div> 
 **/
	
	
}
