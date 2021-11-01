package com.bjworld.groupware.common.vo;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.net.httpserver.HttpContext;

import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;

public class DefaultVO implements Serializable {

	private static final long serialVersionUID = 2616971895328050108L;

	private String searchCondition = "";

	/** 현재페이지 */
	private int pageIndex = 1;

	/** 페이지갯수 */
	private int pageUnit = 10;

	/** 페이지사이즈 */
	private int pageSize = 20;

	/** firstIndex */
	private int firstIndex = 1;

	/** lastIndex */
	private int lastIndex = 1;
	
	private int startIndex = -1;
	private int endIndex = 10;
	private String orderBy;
	private String sortColumn;
	private String sortColumnDir;
	
	private String isUserAdmin;
	
	private String dataRequestUserSeq;

	private int rowNum = 1;
	
	
	private String dbEncString;
	
	@JsonIgnore
	public String getDbEncString() {
		return "gi625@3vet1#";
	}

	public String getDataRequestUserSeq() {
		return dataRequestUserSeq;
	}

	public void setDataRequestUserSeq(String dataRequestUserSeq) {
		this.dataRequestUserSeq = dataRequestUserSeq;
	}

	public String getDraw() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (!EgovStringUtil.isEmpty(req.getParameter("draw"))) {
			return req.getParameter("draw");
		}
		return "1";
	}

	@JsonIgnore
	public int getStartIndex() {
		if(this.startIndex != -1)
			return this.startIndex;
		
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		
		if (!StringUtils.isEmpty(req.getParameter("pn"))) {
			try {
				int pn = Integer.parseInt(req.getParameter("pn"));
				if(pn != 0)
					pn = pn - 1;
				
				int length = getEndIndex();
				return pn * length;
			} catch (Exception e) {
				return 0;
			}
		}
		
		if (!EgovStringUtil.isEmpty(req.getParameter("start"))) {
			try {
				String length = req.getParameter("start");
				return Integer.valueOf(length);
			} catch (Exception e) {
				return 0;
			}
		}
		return 0;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	@JsonIgnore
	public int getEndIndex() {
		if(this.endIndex != 10)
			return this.endIndex;
		
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (!EgovStringUtil.isEmpty(req.getParameter("length"))) {
			try {
				return Integer.valueOf(req.getParameter("length"));
			} catch (Exception e) {
				return this.endIndex;
			}
		}
		return this.endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/** recordCountPerPage */
	private int recordCountPerPage = 10;

	@JsonIgnore
	public String getSearchCondition() {
		if(!EgovStringUtil.isEmpty(this.searchCondition))
			return this.searchCondition;
		else
		{
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			return req.getParameter("search[value]");
		}
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@JsonIgnore
	public int getPageIndex() {

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if (!EgovStringUtil.isEmpty(req.getParameter("pn"))) {
			try {
				return Integer.valueOf(req.getParameter("pn"));
			} catch (Exception e) {
				return 1;
			}
		}
		return 1;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}		
	
	@JsonIgnore
	public String getSortColumn() {
		
		if(!EgovStringUtil.isEmpty(this.sortColumn))
			return this.sortColumn;
		else {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			if (!EgovStringUtil.isEmpty(req.getParameter("sortColumn"))) {
				return req.getParameter("sortColumn");
			}
		}
		return "";
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	@JsonIgnore
	public String getSortColumnDir() {
		
		if(!EgovStringUtil.isEmpty(this.sortColumnDir))
			return this.sortColumnDir;
		else {
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			if (!EgovStringUtil.isEmpty(req.getParameter("sortColumnDir"))) {
				return req.getParameter("sortColumnDir");
			}		
		}
		return "";
	}

	public void setSortColumnDir(String sortColumnDir) {			
		this.sortColumnDir = sortColumnDir;
	}

	@JsonIgnore
	public String getOrderBy()
	{
		if(!EgovStringUtil.isEmpty(getSortColumn()))
		{
			String dir = getSortColumnDir();
			if(EgovStringUtil.isEmpty(dir))
				dir = ""; //null 이 들어올 수도 있어서 "" 처리
			
			return "ORDER BY " + getSortColumn() + " " + getSortColumnDir();
		}
		
		try
		{
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String columnIndex = req.getParameter("order[0][column]");
			String columnDir = req.getParameter("order[0][dir]");
			String columnName = req.getParameter("columns["+columnIndex+"][data]");
			if(columnName.equals("function")) {
				columnName= req.getParameter("columns["+columnIndex+"][name]");
			}
			if(EgovStringUtil.isEmpty(columnName))
				columnName = "seq";
			if(EgovStringUtil.isEmpty(columnDir))
				columnDir = "desc";
				
			return "ORDER BY " + columnName + " " + columnDir;
		}
		catch(Exception ex)
		{
			return "";
		}
	}	
	
	public void setIsUserAdmin(String isUserAdmin)
	{
		this.isUserAdmin = isUserAdmin;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
