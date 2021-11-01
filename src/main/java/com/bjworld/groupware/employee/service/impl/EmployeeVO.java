package com.bjworld.groupware.employee.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class EmployeeVO extends DefaultVO {
	private String seq;
	private String empOrgSeq;
	private String empName;
	private String empPosition;
	private String empPhone;
	private String empEmail;
	private String empJob;
	private String empOrgName;
	private String empSort;

	public String getEmpSort() {
		return empSort;
	}

	public void setEmpSort(String empSort) {
		this.empSort = empSort;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getEmpOrgSeq() {
		return empOrgSeq;
	}

	public void setEmpOrgSeq(String empOrgSeq) {
		this.empOrgSeq = empOrgSeq;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPosition() {
		return empPosition;
	}

	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpJob() {
		return empJob;
	}

	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	public String getEmpOrgName() {
		return empOrgName;
	}

	public void setEmpOrgName(String empOrgName) {
		this.empOrgName = empOrgName;
	}

}
