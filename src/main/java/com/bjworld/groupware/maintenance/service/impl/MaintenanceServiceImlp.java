package com.bjworld.groupware.maintenance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.maintenance.service.impl.MaintenanceMapper;
import com.bjworld.groupware.maintenance.service.impl.MaintenanceVO;
import com.bjworld.groupware. maintenance.service. MaintenanceService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("maintenanceService")

public class MaintenanceServiceImlp  extends EgovAbstractServiceImpl implements  MaintenanceService  {
	
	@Resource(name = "maintenanceMapper")
	private MaintenanceMapper maintenanceMapper;

	@Override
	public void mergeMaintenance(MaintenanceVO paramVO) {
		maintenanceMapper.mergeMaintenance(paramVO);// TODO Auto-generated method stub
		
	}
	
	public MaintenanceVO selectMaintenance(MaintenanceVO paramVO) throws Exception {
        return maintenanceMapper.selectMaintenance(paramVO);
    }


	@Override
	public List<MaintenanceVO> selectMaintenanceList(MaintenanceVO paramVO) throws Exception {
		
		return maintenanceMapper.selectMaintenanceList(paramVO);
	}
	

	@Override
	public void deleteMaintenance(MaintenanceVO paramVO) throws Exception {
		maintenanceMapper.deleteMaintenance(paramVO);
		
	}

	@Override
	public void updateMaintenance(MaintenanceVO paramVO) throws Exception {
		maintenanceMapper.updateMaintenance(paramVO);
		
	}
	
	 @Override
	    public Integer selectMaintenanceListTotCnt(MaintenanceVO paramVO) throws Exception {
	        return maintenanceMapper.selectMaintenanceListTotCnt(paramVO);
	    }

	@Override
	public void updateProStateMaintenance(MaintenanceVO paramVO) throws Exception {
		maintenanceMapper.updateProStateMaintenance(paramVO);
		
	}

}
