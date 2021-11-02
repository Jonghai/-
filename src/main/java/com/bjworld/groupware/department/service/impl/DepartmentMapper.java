package com.bjworld.groupware.department.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.bjworld.groupware.adminuser.service.impl.AdminUserMapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("departmentMapper")
public interface DepartmentMapper {
	
	void mergeDepartment(String deptname) ;
	
	List<?> selectDepartmentList();
	

}
