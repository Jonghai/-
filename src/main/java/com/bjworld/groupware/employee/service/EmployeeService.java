package com.bjworld.groupware.employee.service;

import java.util.List;


import com.bjworld.groupware.employee.service.impl.EmployeeVO;

public interface EmployeeService {
	
	void mergeEmployee(EmployeeVO paramVO);
	
	EmployeeVO selectEmployee(EmployeeVO paramVO) throws Exception;
	

	List<EmployeeVO> selectEmployeeList(EmployeeVO paramVO) throws Exception;

	void deleteEmployee(EmployeeVO paramVO) throws Exception;


	void updateEmployee(EmployeeVO paramVO) throws Exception;

	Integer selectEmployeeListTotCnt(EmployeeVO paramVO) throws Exception;




}

