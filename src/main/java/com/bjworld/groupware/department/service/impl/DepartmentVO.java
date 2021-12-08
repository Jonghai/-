package com.bjworld.groupware.department.service.impl;

public class DepartmentVO{
	private String seq;
	private String parentSeq;
	private String deptName;
	private String parentDeptName;
	
	
	public String getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getParentDeptName() {
		return parentDeptName;
	}
	
	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
}
