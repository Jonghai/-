package com.bjworld.groupware.projectattach.service.impl;

public class ProjectAttachVO {
	
	private String seq;
	private String projectSeq;
	private String oriFilename;
	private String saveFilename;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getProjectSeq() {
		return projectSeq;
	}
	public void setProjectSeq(String projectSeq) {
		this.projectSeq = projectSeq;
	}
	public String getOriFilename() {
		return oriFilename;
	}
	public void setOriFilename(String oriFilename) {
		this.oriFilename = oriFilename;
	}
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	
	

}
