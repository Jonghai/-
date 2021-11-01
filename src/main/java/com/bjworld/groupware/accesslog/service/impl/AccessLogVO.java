package com.bjworld.groupware.accesslog.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class AccessLogVO extends DefaultVO {
	private String seq;
	private String userId;
	private String userName;
	private String deptSeq;
	private String deptName;
	private String menuSeq;
	private String link;
	private String logContents;
	private String regDate;
	private String regYYYYMMDD;
	private String remoteIP;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptSeq() {
		return deptSeq;
	}

	public void setDeptSeq(String deptSeq) {
		this.deptSeq = deptSeq;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLogContents() {
		return logContents;
	}

	public void setLogContents(String logContents) {
		this.logContents = logContents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegYYYYMMDD() {
		return regYYYYMMDD;
	}

	public void setRegYYYYMMDD(String regYYYYMMDD) {
		this.regYYYYMMDD = regYYYYMMDD;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

}
