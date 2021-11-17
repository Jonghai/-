package com.bjworld.groupware.employee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.employee.service.impl.EmployeeMapper;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
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
	
	public EmployeeVO selectEmployee(EmployeeVO paramVO) throws Exception {
        return employeeMapper.selectEmployee(paramVO);
    }


	@Override
	public List<EmployeeVO> selectEmployeeList(EmployeeVO paramVO) throws Exception {
		
		return employeeMapper.selectEmployeeList(paramVO);
	}
	

	@Override
	public void deleteEmployee(EmployeeVO paramVO) throws Exception {
		employeeMapper.deleteEmployee(paramVO);
		
	}

	@Override
	public void updateEmployee(EmployeeVO paramVO) throws Exception {
		employeeMapper.updateEmployee(paramVO);
		
	}
	
	 @Override
	    public Integer selectEmployeeListTotCnt(EmployeeVO paramVO) throws Exception {
	        return employeeMapper.selectEmployeeListTotCnt(paramVO);
	    }
	 


	
}
