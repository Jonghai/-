package com.bjworld.groupware.project.service.impl;

import java.util.List;

import com.bjworld.groupware.projectattach.service.impl.ProjectAttachVO;

public class ProjectVO {
	private String seq;
	private String csSeq;
	private String projectName;
	private String projectStart;
	private String projectEnd;
	private String projectExp;
	private String csName;
	
	private String [] attachSaveFileName;
	private String [] attachOriFileName;
	private String [] attachFileSize;
	
	private List<ProjectAttachVO> projectattachlist;
	
	public String[] getAttachSaveFileName() {
		return attachSaveFileName;
	}
	public void setAttachSaveFileName(String[] attachSaveFileName) {
		this.attachSaveFileName = attachSaveFileName;
	}
	public String[] getAttachOriFileName() {
		return attachOriFileName;
	}
	public void setAttachOriFileName(String[] attachOriFileName) {
		this.attachOriFileName = attachOriFileName;
	}
	public String[] getAttachFileSize() {
		return attachFileSize;
	}
	public void setAttachFileSize(String[] attachFileSize) {
		this.attachFileSize = attachFileSize;
	}
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
	public List<ProjectAttachVO> getProjectattachlist() {
		return projectattachlist;
	}
	public void setProjectattachlist(List<ProjectAttachVO> projectattachlist) {
		this.projectattachlist = projectattachlist;
	}
}
