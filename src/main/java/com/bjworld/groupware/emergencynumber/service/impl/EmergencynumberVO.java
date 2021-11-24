package com.bjworld.groupware.emergencynumber.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class EmergencynumberVO extends DefaultVO {
	private String seq;
	private String employeeSeq;
	private String emerNum;
	private String empName;
	private String empRel;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getEmerNum() {
		return emerNum;
	}
	public void setEmerNum(String emerNum) {
		this.emerNum = emerNum;
	}
	public String getEmployeeSeq() {
		return employeeSeq;
	}
	public void setEmployeeSeq(String employeeSeq) {
		this.employeeSeq = employeeSeq;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpRel() {
		return empRel;
	}
	public void setEmpRel(String empRel) {
		this.empRel = empRel;
	}


}
