package com.bjworld.groupware.employee.service.impl;

import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("employeeMapper")
public interface EmployeeMapper{

	List<EmployeeVO> selectEmployeeList(EmployeeVO paramVO) throws Exception;
	
	Integer selectEmployeeListTotCnt(EmployeeVO paramVO) throws Exception;
	
	void mergeEmployee(EmployeeVO paramVO) throws Exception;

    void insertEmployee(EmployeeVO paramVO) throws Exception;

    void updateEmployee(EmployeeVO paramVO) throws Exception;
	
	EmployeeVO selectEmployee(EmployeeVO paramVO) throws Exception;
	
	void deleteEmployee(EmployeeVO paramVO) throws Exception;
	
	void updateEmployeeSort(EmployeeVO paramVO) throws Exception;
}
