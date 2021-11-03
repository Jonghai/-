package com.bjworld.groupware.employee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.employee.service.EmployeeService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("employeeService")

public class EmployeeServiceImpl extends EgovAbstractServiceImpl implements EmployeeService {
	@Resource(name = "employeeMapper")
	private EmployeeMapper employeeMapper;

	@Override
	public void mergeEmployee(EmployeeVO paramVO) {
		employeeMapper.mergeEmployee(paramVO);// TODO Auto-generated method stub
		

	}


	@Override
	public List<?> selectEmployeeList() {
		
		return employeeMapper.selectEmployeeList();
	}

	
}
