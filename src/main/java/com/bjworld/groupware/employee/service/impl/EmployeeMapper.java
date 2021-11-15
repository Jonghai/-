package com.bjworld.groupware.employee.service.impl;

import java.util.List;


import com.bjworld.groupware.employee.service.impl.EmployeeVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("employeeMapper")
public interface EmployeeMapper {
	
	void mergeEmployee(EmployeeVO paramVO);
	
	EmployeeVO selectEmployee(EmployeeVO paramVO) throws Exception;
	
	void updateEmployee(EmployeeVO paramVO) throws Exception;
	
	List<EmployeeVO> selectEmployeeList(EmployeeVO paramVO) throws Exception;
	
	Integer selectEmployeeListTotCnt(EmployeeVO paramVO) throws Exception;

	
	void deleteEmployee(EmployeeVO paramVO) throws Exception;

}

