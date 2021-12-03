package com.bjworld.groupware.customerstaff.service;

import java.util.List;

import com.bjworld.groupware.customerstaff.service.impl.CustomerStaffVO;

public interface CustomerStaffService {

	void mergeCustomerStaff(CustomerStaffVO paramVO);

	CustomerStaffVO selectCustomerStaff(CustomerStaffVO paramVO) throws Exception;
	

	List<CustomerStaffVO> selectCustomerStaffList(CustomerStaffVO paramVO) throws Exception;

	void deleteCustomerStaff(CustomerStaffVO paramVO) throws Exception;


	void updateCustomerStaff(CustomerStaffVO paramVO) throws Exception;

	Integer selectCustomerStaffListTotCnt(CustomerStaffVO paramVO) throws Exception;

}
