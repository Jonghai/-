package com.bjworld.groupware.emergencynumber.service;

import java.util.List;

import com.bjworld.groupware.emergencynumber.service.impl.EmergencynumberVO;

public interface EmergencynumberService {
	void mergeEmergencynumber(EmergencynumberVO paramVO);

	List<?> selectEmergencynumberList();

	 void deleteEmergencynumber(EmergencynumberVO paramVO) throws Exception;
	

	
}
