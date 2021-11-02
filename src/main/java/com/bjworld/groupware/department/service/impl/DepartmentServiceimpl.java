package com.bjworld.groupware.department.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.department.service.DepartmentService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("departmentService")

public class DepartmentServiceimpl extends EgovAbstractServiceImpl implements DepartmentService {
	@Resource(name = "departmentMapper")
	private DepartmentMapper departmentMapper;

	@Override
	public void mergeDepartment(String deptname) {
		departmentMapper.mergeDepartment(deptname);// TODO Auto-generated method stub

	
	}

	@Override
	public List<?> selectDepartmentList() {
		
		return departmentMapper.selectDepartmentList();
	}
	
}
