package com.bjworld.groupware.emergencynumber.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.emergencynumber.service.EmergencynumberService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("emergencynumberService")

public class EmergencynumberServiceImpl extends EgovAbstractServiceImpl implements EmergencynumberService {
	@Resource(name = "emergencynumberMapper")
	private EmergencynumberMapper emergencynumberMapper;
	
	public void mergeEmergencynumber(EmergencynumberVO paramVO) {
		emergencynumberMapper.mergeEmergencynumber(paramVO);// TODO Auto-generated method stub

	
	}

	@Override
	public List<?> selectEmergencynumberList() {
		
		return emergencynumberMapper.selectEmergencynumberList();
	}

	@Override
	public void deleteEmergencynumber(EmergencynumberVO paramVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	
	}

	
		
	
	

