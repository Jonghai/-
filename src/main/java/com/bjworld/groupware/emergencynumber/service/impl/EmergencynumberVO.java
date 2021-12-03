package com.bjworld.groupware.emergencynumber.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class EmergencynumberVO {
	private String seq;
	private String employeeSeq;
 	private String emerNum;
	private String emerName;
	private String empRel;
	private String empName;
	private String empPhone;
	private String deptName;
	
	
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
	public String getEmerName() {
		return emerName;
	}
	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}
	public String getEmpRel() {
		return empRel;
	}
	public void setEmpRel(String empRel) {
		this.empRel = empRel;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
