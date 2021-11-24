package com.bjworld.groupware.department.service.impl;

import java.util.List;

import com.bjworld.groupware.department.service.impl.DepartmentVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("departmentMapper")
public interface DepartmentMapper {
	
	
	List<?> selectDepartmentList();
	
DepartmentVO selectDepartment(DepartmentVO paramVO) throws Exception;
	
	void updateDepartment(DepartmentVO paramVO) throws Exception;
	
	List<DepartmentVO> selectDepartmentList(DepartmentVO paramVO) throws Exception;
	
	Integer selectDepartmentListTotCnt(DepartmentVO paramVO) throws Exception;

	
	void deleteDepartment(DepartmentVO paramVO) throws Exception;

	void mergeDepartment(DepartmentVO paramVO);


}
