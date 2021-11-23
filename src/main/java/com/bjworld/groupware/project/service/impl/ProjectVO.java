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
	public String getprojectName() {
		return projectName;
	}
	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}
	public String getprojectStart() {
		return projectStart;
	}
	public void setprojectStart(String projectStart) {
		this.projectStart = projectStart;
	}
	public void setprojectEnd(String projectEnd) {
		this.projectEnd = projectEnd;
	}
	public String getprojectEnd() {
		return projectEnd;
	}
	public String getprojectExp() {
		return projectExp;
	}
	public void setprojectExp(String projectExp) {
		this.projectExp = projectExp;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}
}
