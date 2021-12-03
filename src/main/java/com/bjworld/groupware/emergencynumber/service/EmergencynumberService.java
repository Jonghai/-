package com.bjworld.groupware.emergencynumber.service;

import java.util.List;

import com.bjworld.groupware.emergencynumber.service.impl.EmergencynumberVO;

public interface EmergencynumberService {
	void mergeEmergencynumber(EmergencynumberVO paramVO);

	List<?> selectEmergencynumberList(EmergencynumberVO paramVO) throws Exception;

	 void deleteEmergencynumber(EmergencynumberVO paramVO) throws Exception;

	Integer selectEmergencynumberListTotCnt(EmergencynumberVO paramVO) throws Exception;

	void updateEmergencynumber(EmergencynumberVO paramVO) throws Exception;

	List<?> selectEmergencynumberList();

	EmergencynumberVO selectEmergencynumber(EmergencynumberVO paramVO) throws Exception;



	
}
