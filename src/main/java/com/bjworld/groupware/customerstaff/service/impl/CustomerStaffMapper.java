package com.bjworld.groupware.customerstaff.service.impl;

import java.util.List;

import com.bjworld.groupware.customerstaff.service.impl.CustomerStaffVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("customerstaffMapper")
public interface CustomerStaffMapper {
	void mergeCustomerStaff(CustomerStaffVO paramVO);
	
	CustomerStaffVO selectCustomerStaff(CustomerStaffVO paramVO) throws Exception;
	
	void updateCustomerStaff(CustomerStaffVO paramVO) throws Exception;
	
	List<CustomerStaffVO> selectCustomerStaffList(CustomerStaffVO paramVO) throws Exception;
	
	Integer selectCustomerStaffListTotCnt(CustomerStaffVO paramVO) throws Exception;

	
	void deleteCustomerStaff(CustomerStaffVO paramVO) throws Exception;

}
