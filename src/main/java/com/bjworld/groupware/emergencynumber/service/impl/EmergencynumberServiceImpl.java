package com.bjworld.groupware.emergencynumber.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.emergencynumber.service.EmergencynumberService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("emergencynumberService")

public class EmergencynumberServiceImpl extends EgovAbstractServiceImpl implements EmergencynumberService {
	@Resource(name = "emergencynumberMapper")
	private EmergencynumberMapper emergencynumberMapper;
	
	@Override
	public void mergeEmergencynumber(EmergencynumberVO paramVO) {
		emergencynumberMapper.mergeEmergencynumber(paramVO);// TODO Auto-generated method stub

	}

	public EmergencynumberVO selectEmergencynumber(EmergencynumberVO paramVO) {
        return emergencynumberMapper.selectEmergencynumber(paramVO);
    }
	
	@Override
	public List<EmergencynumberVO> selectEmergencynumberList(EmergencynumberVO paramVO) throws Exception {
		
		return emergencynumberMapper.selectEmergencynumberList(paramVO);
	}

	@Override
	public void deleteEmergencynumber(EmergencynumberVO paramVO) throws Exception {
		emergencynumberMapper.deleteEmergencynumber(paramVO);
		
	}


	@Override
	public Integer selectEmergencynumberListTotCnt(EmergencynumberVO paramVO) throws Exception {
		
		return emergencynumberMapper.selectEmergencynumberListTotCnt(paramVO);
	}

	@Override
	public void updateEmergencynumber(EmergencynumberVO paramVO) throws Exception  {
		emergencynumberMapper.updateEmergencynumber(paramVO);
		
	}

	@Override
	public List<?> selectEmergencynumberList() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	}

	
		
	
	

