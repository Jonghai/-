package com.bjworld.groupware.employee.service.impl;

import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("employeeMapper")
public interface EmployeeMapper {
	
	void mergeEmployee(EmployeeVO paramVO);
	
	List<?> selectEmployeeList();
	

}

