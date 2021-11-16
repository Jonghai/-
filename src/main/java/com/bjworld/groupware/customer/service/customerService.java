package com.bjworld.groupware.customer.service;

import java.util.List;

import com.bjworld.groupware.customer.service.impl.CustomerVO;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;

public interface customerService {

	CustomerVO selectCustomer(CustomerVO paramVO) throws Exception;

	List<CustomerVO> selectCustomerList(CustomerVO paramVO) throws Exception;

	void mergeCustomer(CustomerVO paramVO) throws Exception;
	
	void updateCustomer(CustomerVO paramVO) throws Exception;
	
	void deleteCustomer(CustomerVO paramVO) throws Exception;
	
	Integer selectCustomerListTotCnt(CustomerVO paramVO) throws Exception;
}

/*
 * public interface CustomerService{ void mergeCustomer(CustomerUserVO paramVO)
 * throws Exception;
 * 
 * void insertCustomer(CustomerUserVO paramVO) throws Exception;
 * 
 * void updateCustomer(CustomerUserVO paramVO) throws Exception;
 * 
 * CustomerUserVO selectCustomer(CustomerUserVO paramVO) throws Exception;
 * 
 * void deleteCustomer(CustomerUserVO paramVO) throws Exception;
 * 
 * List<?> selectCustomerList();
 * 
 * CustomerUserVO selectCustomerUser(CustomerUserVO paramVO) throws Exception; }
 */
