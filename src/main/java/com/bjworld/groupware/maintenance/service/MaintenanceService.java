package com.bjworld.groupware.maintenance.service;

import java.util.List;

import com.bjworld.groupware.maintenance.service.impl.MaintenanceVO;

public interface MaintenanceService {
	
	void mergeMaintenance(MaintenanceVO paramVO);
	
	MaintenanceVO selectMaintenance(MaintenanceVO paramVO) throws Exception;
	

	List<MaintenanceVO> selectMaintenanceList(MaintenanceVO paramVO) throws Exception;

	void deleteMaintenance(MaintenanceVO paramVO) throws Exception;


	void updateMaintenance(MaintenanceVO paramVO) throws Exception;

	Integer selectMaintenanceListTotCnt(MaintenanceVO paramVO) throws Exception;

	void updateProStateMaintenance(MaintenanceVO paramVO) throws Exception;

}
