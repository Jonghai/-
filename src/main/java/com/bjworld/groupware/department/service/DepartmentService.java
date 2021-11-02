package com.bjworld.groupware.department.service;

import java.util.List;

public interface DepartmentService {
	void mergeDepartment(String deptname);

	List<?> selectDepartmentList();

}
