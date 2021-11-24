package com.bjworld.groupware.department.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.department.service.impl.DepartmentMapper;
import com.bjworld.groupware.department.service.impl.DepartmentVO;
import com.bjworld.groupware.department.service.DepartmentService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("departmentService")

public class DepartmentServiceImpl extends EgovAbstractServiceImpl implements DepartmentService {
	@Resource(name = "departmentMapper")
	private DepartmentMapper departmentMapper;

	@Override
	public void mergeDepartment(DepartmentVO paramVO) {
		departmentMapper.mergeDepartment(paramVO);// TODO Auto-generated method stub
		
	}
	
	public DepartmentVO selectDepartment(DepartmentVO paramVO) throws Exception {
        return departmentMapper.selectDepartment(paramVO);
    }


	@Override
	public List<DepartmentVO> selectDepartmentList(DepartmentVO paramVO) throws Exception {
		
		return departmentMapper.selectDepartmentList(paramVO);
	}
	

	@Override
	public void deleteDepartment(DepartmentVO paramVO) throws Exception {
		departmentMapper.deleteDepartment(paramVO);
		
	}

	@Override
	public void updateDepartment(DepartmentVO paramVO) throws Exception {
		departmentMapper.updateDepartment(paramVO);
		
	}
	
	 @Override
	    public Integer selectDepartmentListTotCnt(DepartmentVO paramVO) throws Exception {
	        return departmentMapper.selectDepartmentListTotCnt(paramVO);
	    }

	@Override
	public List<?> selectDepartmentList() {
		// TODO Auto-generated method stub
		return null;
	}





	
}
