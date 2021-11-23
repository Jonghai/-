package com.bjworld.groupware.project.service.impl;

public class ProjectVO {
	private String seq;
	private String csSeq;
	private String projectName;
	private String projectStart;
	private String projectEnd;
	private String projectExp;
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStart() {
		return projectStart;
	}
	public void setProjectStart(String projectStart) {
		this.projectStart = projectStart;
	}
	public void setProjectEnd(String projectEnd) {
		this.projectEnd = projectEnd;
	}
	public String getProjectEnd() {
		return projectEnd;
	}
	public String getProjectExp() {
		return projectExp;
	}
	public void setProjectExp(String projectExp) {
		this.projectExp = projectExp;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
}
