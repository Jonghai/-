package com.bjworld.groupware.department.service;

import java.util.List;

import com.bjworld.groupware.department.service.impl.DepartmentVO;

public interface DepartmentService {
	
	void mergeDepartment(DepartmentVO paramVO);
	
	DepartmentVO selectDepartment(DepartmentVO paramVO) throws Exception;
	

	List<DepartmentVO> selectDepartmentList(DepartmentVO paramVO) throws Exception;

	void deleteDepartment(DepartmentVO paramVO) throws Exception;


	void updateDepartment(DepartmentVO paramVO) throws Exception;

	Integer selectDepartmentListTotCnt(DepartmentVO paramVO) throws Exception;

	List<?> selectDepartmentList();


}

