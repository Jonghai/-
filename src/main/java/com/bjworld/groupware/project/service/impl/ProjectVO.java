package com.bjworld.groupware.project.service.impl;

public class ProjectVO {
	private String seq;
	private String csSeq;
	private String pjName;
	private String pjStart;
	private String pjEnd;
	private String pjExp;
	private String csName;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCsSeq() {
		return csSeq;
	}
	public void setCsSeq(String csSeq) {
		this.csSeq = csSeq;
	}
	public String getPjName() {
		return pjName;
	}
	public void setPjName(String pjName) {
		this.pjName = pjName;
	}
	public String getPjStart() {
		return pjStart;
	}
	public void setPjStart(String pjStart) {
		this.pjStart = pjStart;
	}
	public void setPjEnd(String pjEnd) {
		this.pjEnd = pjEnd;
	}
	public String getPjEnd() {
		return pjEnd;
	}
	public String getPjExp() {
		return pjExp;
	}
	public void setPjExp(String pjExp) {
		this.pjExp = pjExp;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
}
