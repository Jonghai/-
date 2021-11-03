package com.bjworld.groupware.employee.service;

import java.util.List;

import com.bjworld.groupware.employee.service.impl.EmployeeVO;

public interface EmployeeService {
	void mergeEmployee(EmployeeVO paramVO);
	

	List<?> selectEmployeeList();


}

